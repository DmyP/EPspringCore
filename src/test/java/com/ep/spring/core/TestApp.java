package com.ep.spring.core;

import com.ep.spring.core.beans.Client;
import com.ep.spring.core.beans.Event;
import com.ep.spring.core.beans.EventType;
import com.ep.spring.core.loggers.EventLogger;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class TestApp {
    private static final String MSG = "Hello";

    @Test
    public void testClientNameSubstitution() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Client client = new Client("25", "Bob");
        MockLogger mockLogger = new MockLogger();

        App app = new App(client, mockLogger, Collections.emptyMap());
        Event event = new Event(new Date(), DateFormat.getDateTimeInstance());

        invokeLogEvent(app, null, event, MSG + " " + client.getId());
        assertTrue(mockLogger.getEvent().getMsg().contains(MSG));
        assertTrue(mockLogger.getEvent().getMsg().contains(client.getFullName()));

        invokeLogEvent(app, null, event, MSG + " 0");
        assertTrue(mockLogger.getEvent().getMsg().contains(MSG));
        assertFalse(mockLogger.getEvent().getMsg().contains(client.getFullName()));
    }

    @Test
    public void testCorrectLoggerCall() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Client client = new Client("25", "Bob");
        MockLogger defaultLogger = new MockLogger();
        MockLogger infoLogger = new MockLogger();

        @SuppressWarnings("serial")
        App app = new App(client, defaultLogger, new HashMap<EventType, EventLogger>() {{
            put(EventType.INFO, infoLogger);
        }});

        Event event = new Event(new Date(), DateFormat.getDateTimeInstance());

        invokeLogEvent(app, null, event, MSG + " " + client.getId());
        assertNotNull(defaultLogger.getEvent());
        assertNull(infoLogger.getEvent());

        defaultLogger.setEvent(null);
        infoLogger.setEvent(null);

        invokeLogEvent(app, EventType.ERROR, event, MSG + " " + client.getId());
        assertNotNull(defaultLogger.getEvent());
        assertNull(infoLogger.getEvent());

        defaultLogger.setEvent(null);
        infoLogger.setEvent(null);

        invokeLogEvent(app, EventType.INFO, event, MSG + " 0");
        assertNull(defaultLogger.getEvent());
        assertNotNull(infoLogger.getEvent());
    }


    private void invokeLogEvent(App app, EventType type, Event event, String message) throws NoSuchMethodException,
        IllegalAccessException, InvocationTargetException {
        Method method = app.getClass().getDeclaredMethod("logEvent", EventType.class, Event.class, String.class);
        method.setAccessible(true);
        method.invoke(app, type, event, message);
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

        public void setEvent(Event event) {
            this.event = event;
        }
    }

}