package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class TestFileEventLogger {
    private File file;

    @Before
    public void createFile() throws IOException {
        this.file = File.createTempFile("test", "FileEventLogger");
    }

    @After
    public void removeFile() throws IOException {
        file.delete();
    }

    @Test
    public void testInit() throws IOException {
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitFail() throws IOException {
        file.setReadOnly();
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();
    }

    @Test
    public void testLogEvent() throws IOException {
        Event event = new Event(new Date(), DateFormat.getDateInstance());
        FileEventLogger logger = new FileEventLogger(file.getAbsolutePath());
        logger.init();

        String contents = FileUtils.readFileToString(this.file, Charset.defaultCharset());
        assertTrue("File is empty initially", contents.isEmpty());

        logger.logEvent(event);

        contents = FileUtils.readFileToString(this.file, Charset.defaultCharset());
        assertFalse("File is not empty after log event", contents.isEmpty());
    }
}