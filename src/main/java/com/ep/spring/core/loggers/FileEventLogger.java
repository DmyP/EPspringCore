package com.ep.spring.core.loggers;

import com.ep.spring.core.beans.Event;
import java.io.*;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class FileEventLogger extends AbstractLogger {

    private File file;

    private String filename;

    public FileEventLogger(String filename) {
        this.filename = filename;
    }

    public void init() throws IOException {
        file = new File(filename);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + filename);
        } else if (!file.exists()) {
            file.createNewFile();
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", Charset.defaultCharset(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}