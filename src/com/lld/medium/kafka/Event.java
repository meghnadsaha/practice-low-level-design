package com.lld.medium.kafka;

public class Event {
    private String id;
    private String type;
    private  String data;

    public Event ( String id , String type , String data ) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    public String getId () {
        return id;
    }

    public String getType () {
        return type;
    }

    public String getData () {
        return data;
    }
}
