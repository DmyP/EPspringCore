package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;

public interface EventLogger {
    public void logEvent(Event event);
}
