package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;

import java.util.Collection;
import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public Collection<EventLogger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers){
            eventLogger.logEvent(event);
        }
    }
}
