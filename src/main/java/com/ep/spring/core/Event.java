package com.ep.spring.core;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    public Event(Date date, DateFormat df) {
        this.id = (int) (Math.random() * 100);
        this.date = date;
        this.df = df;
    }

    public Event(String msg, Date date, DateFormat df) {
        this.id = (int) (Math.random() * 100);
        this.msg = msg;
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + date +
                ", df=" + df +
                '}';
    }
}
