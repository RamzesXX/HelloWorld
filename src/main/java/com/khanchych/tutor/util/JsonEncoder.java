package com.khanchych.tutor.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.encoder.EncoderBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ArrayUtils;

// Mostly based off of
// http://stash.corp.web:7990/projects/EST/repos/partner-fulfillment/browse/src/main/java/com/cengage/fulfillment/logging/JsonEncoder.java
// and
// http://stash.corp.web:7990/projects/BOOT/repos/jaxrs-boot/browse/core/src/main/java/com/cengage/jaxrs/boot/core/logback/JsonEncoder.java
public class JsonEncoder extends EncoderBase<ILoggingEvent> {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final byte[] LINE_SEPARATOR_BYTES = System.getProperty("line.separator")
                                                             .getBytes(StandardCharsets.UTF_8);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Limit to 50 levels to not overwhelm Splunk
    private static final int STACK_TRACE_LINE_LIMIT = 50;

    @Override
    public byte[] encode(ILoggingEvent event) {
        final Map<String, Object> eventJson = new HashMap<>();
        eventJson.put("@timestamp", Instant.ofEpochMilli(event.getTimeStamp()).toString());
        eventJson.put("logger_name", event.getLoggerName());
        eventJson.put("thread_name", event.getThreadName());
        eventJson.put("level",
                      Optional.ofNullable(event.getLevel())
                              .map(Object::toString)
                              .orElse(null));
        eventJson.put("message", event.getFormattedMessage());
        eventJson.put("mdc", formatMdc(event));
        if (event.getCallerData() != null && event.getCallerData().length > 0) {
            StackTraceElement stackTraceElement = event.getCallerData()[0];
            eventJson.put("class", stackTraceElement.getClassName());
            eventJson.put("method", stackTraceElement.getMethodName());
            eventJson.put("line", stackTraceElement.getLineNumber());
        }
        Optional.ofNullable(event.getThrowableProxy())
                .ifPresent(throwableProxy -> eventJson.put("exception", formatException(throwableProxy)));

        return toJsonBytes(eventJson);
    }

    @Override
    public byte[] footerBytes() {
        return EMPTY_BYTES;
    }

    @Override
    public byte[] headerBytes() {
        return EMPTY_BYTES;
    }

    private Map<String, Object> formatException(IThrowableProxy throwableProxy) {
        final Map<String, Object> exception = new HashMap<>();
        exception.put("message", throwableProxy.getMessage());
        exception.put("type", throwableProxy.getClassName());
        exception.put("stacktrace",
                      Stream.of(throwableProxy.getStackTraceElementProxyArray())
                            .map(StackTraceElementProxy::getSTEAsString)
                            .limit(STACK_TRACE_LINE_LIMIT)
                            .collect(Collectors.toList()));
        return exception;
    }

    private Map<String, Object> formatMdc(ILoggingEvent event) {
        Map<String, Object> mdc = new HashMap<>();

        /* In a happy world, we could do the code below, but thanks to a
        known OpenJDK 1.8 bug (https://bugs.openjdk.java.net/browse/JDK-8148463), it will fail on null values

        return event.getMDCPropertyMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue()));
         */
        event.getMDCPropertyMap().forEach(mdc::put);

        return mdc;
    }

    @SneakyThrows
    private byte[] toJsonBytes(Map<String, Object> eventJson) {
        return ArrayUtils.addAll(
                OBJECT_MAPPER.writeValueAsBytes(eventJson),
                LINE_SEPARATOR_BYTES
        );
    }
}
