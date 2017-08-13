package com.ep.spring.core;

import com.ep.spring.core.loggers.EventLogger;
import com.ep.spring.core.loggers.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private String startupMessage;

    public String getStartupMessage() {
        return startupMessage;
    }

    public void setStartupMessage(String startupMessage) {
        this.startupMessage = startupMessage;
    }

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("springConfig.xml");
        App app = (App) ctx.getBean("app");
        System.out.println(app.startupMessage);

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());
        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if (logger == null){
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    public Map<EventType, EventLogger> getLoggers() {
        return loggers;
    }

    public void setLoggers(Map<EventType, EventLogger> loggers) {
        this.loggers = loggers;
    }
}
