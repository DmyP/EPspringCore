package com.ep.spring.core;

import com.ep.spring.core.Loggers.CacheFileEventLogger;
import com.ep.spring.core.Loggers.ConsoleEventLogger;
import com.ep.spring.core.Loggers.EventLogger;
import com.ep.spring.core.Loggers.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        ctx.close();
    }

    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
