package com.ep.spring.core;

import com.ep.spring.core.Loggers.EventLogger;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class TestApp {

    private static final String MSG = "Hello";

    @Test
    public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Client client = new Client("25", "Bob");
        MockLogger mockLogger = new MockLogger();

        App app = new App(client, mockLogger);

        Event event = new Event(new Date(), DateFormat.getDateTimeInstance());

        invokeLogEvent(app, event, MSG + " " + client.getId());
        assertTrue(mockLogger.getEvent().getMsg().contains(MSG));
        assertTrue(mockLogger.getEvent().getMsg().contains(client.getFullName()));

        invokeLogEvent(app, event, MSG + " 0");
        assertTrue(mockLogger.getEvent().getMsg().contains(MSG));
        assertFalse(mockLogger.getEvent().getMsg().contains(client.getFullName()));
    }

    private void invokeLogEvent(App app, Event event, String message) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Method method = app.getClass().getDeclaredMethod("logEvent", Event.class, String.class);
        method.setAccessible(true);
        method.invoke(app, event, message);
    }

    private class MockLogger implements EventLogger {

        private Event event;

        @Override
        public void logEvent(Event event) {
            this.event = event;
        }

        public Event getEvent() {
            return event;
        }

    };

}
