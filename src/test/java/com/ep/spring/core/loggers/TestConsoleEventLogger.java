package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TestConsoleEventLogger {
    public static final String MSG = "Test message";
    public static final ByteOutputStream outputStream = new ByteOutputStream();

    @Before
    public void setUpStream(){
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void cleanUpStream(){
        System.setOut(null);
    }

    @Test
    public void testLogEvent(){
        ConsoleEventLogger logger = new ConsoleEventLogger();
        Date date = new Date();
        Event event = new Event(date, DateFormat.getDateTimeInstance());
        event.setMsg(MSG);

        logger.logEvent(event);
        assertTrue(outputStream.toString().contains(MSG));
    }
}