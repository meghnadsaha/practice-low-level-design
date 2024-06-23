package com.lld.medium.kafka;

import java.util.ArrayList;
import java.util.List;

public class EventConsumer implements Runnable {

    private EventQueue queue = new EventQueue();
    private List<Server> servers = new ArrayList<>();

    public EventConsumer ( EventQueue queue , List<Server> servers ) {
        this.queue = queue;
        this.servers = servers;
    }

    public void consumeEvents () {
        while (true) {
            System.out.println("\nWaiting for the next event...");
            Event event = queue.getNextEvent();

            System.out.println(
                    "Consumed event: ID=" + event.getId() + ", Type=" + event.getType() + ", Data=" + event.getData());

            for (Server server : servers) {
                System.out.println("\n Notifying server: " + server.getId());
                server.notify(event);
            }
        }
    }

    @Override
    public void run () {
        consumeEvents();
    }

}
