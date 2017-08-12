package com.ep.spring.core.loggers;

import com.ep.spring.core.Event;

public class ConsoleEventLogger implements EventLogger{
    @Override
    public void logEvent(Event event) {
        System.out.println(event.getMsg());
    }
}
