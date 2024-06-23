package com.lld.medium.kafka;

import java.util.HashMap;
import java.util.Map;

public class Broker {
    private String id;
    private Map<String, Topic> topics = new HashMap<>();

    public Broker ( String id ) {
        this.id = id;
    }

    public void addTopic ( Topic topic ) {
        topics.put(topic.getName() , topic);
    }


    public Topic getTopic ( String name ) {
        return topics.get(name);
    }


    public String getId () {
        return id;
    }
}
