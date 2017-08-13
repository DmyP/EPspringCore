package com.ep.spring.core.loggers;

public abstract class AbstractLogger implements EventLogger{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
