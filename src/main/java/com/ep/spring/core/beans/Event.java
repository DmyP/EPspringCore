package com.ep.spring.core.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;

    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);

    public Event(Date date, DateFormat df) {
        this.id = AUTO_ID.incrementAndGet();
        this.date = date;
        this.df = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + msg + '\'' +
                ", date=" + df.format(date) +
                "}\n";
    }
}
