package com.lld.medium.kafka;

import java.util.LinkedList;

public class EventQueue {
    private LinkedList<Event> queue = new LinkedList<>();

    public synchronized void addEvent ( Event event ) {
        queue.add(event);
        notifyAll();
    }


    public synchronized Event getNextEvent ( ) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return queue.poll();
    }

}
