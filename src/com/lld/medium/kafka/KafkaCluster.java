package com.lld.medium.kafka;

import java.util.ArrayList;
import java.util.List;

public class KafkaCluster {
    private List<Broker> brokers = new ArrayList<>();

    public void addBroker ( Broker broker ) {
        brokers.add(broker);
    }

    public Broker getBroker ( String id ) {
        for (Broker broker : brokers) {
            if (broker.getId().equals(id)) {
                return broker;
            }
        }
        return null;
    }

}
