package com.ep.spring.core;

public class ConsoleEventLogger implements EventLogger{
    public void logEvent(Event event) {
        System.out.println(event.getMsg());
    }
}
