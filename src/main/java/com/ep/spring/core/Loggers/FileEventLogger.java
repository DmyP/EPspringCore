package com.ep.spring.core.Loggers;

import com.ep.spring.core.Event;

import java.io.*;

public class FileEventLogger implements EventLogger {
    private String fileName;
    private File file;

    public void init() throws IOException{
        this.file = new File(fileName);

    }

    @Override
    public void logEvent(Event event) {
        try {
            PrintWriter out = new PrintWriter(fileName);
            out.write(event.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
