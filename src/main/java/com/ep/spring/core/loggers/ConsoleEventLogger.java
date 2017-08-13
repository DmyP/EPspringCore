package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;

public class ConsoleEventLogger extends AbstractLogger{
    @Override
    public void logEvent(Event event) {
        System.out.println(event.getMsg());
    }
}
