package com.qunar.fin.listener;

import java.util.Date;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/6/22 20:15
 */
public class Event {

    private String name;

    private Date data;

    private EventListener eventListener;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
        if (eventListener != null) {
            eventListener.onChange();
        }
    }

    public EventListener getEventListener() {
        return eventListener;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("name='").append(name).append('\'');
        sb.append(", data=").append(data);
        sb.append(", eventListener=").append(eventListener);
        sb.append('}');
        return sb.toString();
    }
}
