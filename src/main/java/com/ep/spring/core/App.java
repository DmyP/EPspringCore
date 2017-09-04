package com.ep.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.util.Date;

public class App {
    private Client client;
    private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public App() {
    }

    public void logEvent(Event event) {
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event e1 = new Event(new Date(2017,8,11), DateFormat.getDateTimeInstance());
        Event e2 = new Event(new Date(2017,8,11), DateFormat.getDateTimeInstance());

        e1.setMsg("Some event for 1");
        e2.setMsg("Some event for 2");

        app.logEvent(e1);
        app.logEvent(e2);

    }
}
