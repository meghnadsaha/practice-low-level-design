package com.lld.medium.kafka;

public class Partition {

    private int id;
    private EventQueue events = new EventQueue();


    public Partition ( int id ) {
        this.id = id;
    }

    public void addEvent ( Event event ) {
        events.addEvent(event);
    }

    public Event getNextEvent () {
        return events.getNextEvent();
    }


    public EventQueue getEvents () {
        return events;
    }


}
