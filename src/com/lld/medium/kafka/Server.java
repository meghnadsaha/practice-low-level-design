package com.lld.medium.kafka;

public class Server {

    private String id;

    public Server ( String id ) {
        this.id = id;
    }

    void notify ( Event event ) {
        System.out.println("Server " + id + " notified of event: " + event.getId()
                                   +" type: "+event.getType() +" data: "+event.getData());
    }

    public String getId () {
        return id;
    }
}
