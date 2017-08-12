package com.ep.spring.core.loggers;

import com.ep.spring.core.Event;

public interface EventLogger {
    String getName();
    void logEvent(Event event);
}
