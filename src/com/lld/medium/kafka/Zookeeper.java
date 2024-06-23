package com.lld.medium.kafka;

import java.util.HashMap;
import java.util.Map;

public class Zookeeper {

    private Map<String, Object> metadata = new HashMap<>();

    public void registerBroker ( Broker broker ) {
        metadata.put("broker-" + broker.getId() , broker);
    }

    public void registerTopic ( Topic topic ) {
        metadata.put("topic-" + topic.getName() , topic);
    }

    public Object getMetadata(String key) {
        return metadata.get(key);
    }
}
