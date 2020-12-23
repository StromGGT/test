package com.qunar.fin.listener;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/6/22 20:17
 */
public class EventHandler {

    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EventHandler{");
        sb.append("event=").append(event);
        sb.append('}');
        return sb.toString();
    }
}
