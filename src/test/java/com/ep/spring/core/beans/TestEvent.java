package com.ep.spring.core.beans;

import com.ep.spring.core.beans.Event;
import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;

public class TestEvent {
    @Test
    public void testToString(){
        Date date = new Date();
        DateFormat format = DateFormat.getDateInstance();

        Event event = new Event(date, format);

        String str = event.toString();
        Assert.assertTrue(str.contains(format.format(date)));
    }
}
