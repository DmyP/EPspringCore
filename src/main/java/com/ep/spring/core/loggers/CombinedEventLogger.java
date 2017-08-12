package com.ep.spring.core.loggers;

import com.ep.spring.core.Event;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CombinedEventLogger extends AbstractLogger {
    private Collection<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    public Collection<EventLogger> getLoggers() {
        return Collections.unmodifiableCollection(loggers);
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
