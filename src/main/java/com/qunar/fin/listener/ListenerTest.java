package com.qunar.fin.listener;

import java.util.Date;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/6/22 20:20
 */
public class ListenerTest {

    public static void main(String[] args) {
        EventHandler handler = new EventHandler();

        Event event = new Event();
        event.setName("event");
        event.setData(new Date());

        handler.setEvent(event);

        handler.getEvent().setEventListener(() -> System.out.println("event is change"));
        handler.getEvent().setData(new Date());

    }
}
