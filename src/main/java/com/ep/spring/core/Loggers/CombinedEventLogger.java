package com.ep.spring.core.Loggers;

import com.ep.spring.core.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public List<EventLogger> getLoggers() {
        return loggers;
    }

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
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
