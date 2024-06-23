package com.lld.medium.kafka;

public class EventProducer {

    private Partition partition;

    public EventProducer ( Partition partition ) {
        this.partition = partition;
    }

    public void produceEvent ( Event event ) {
        partition.addEvent(event);
    }
}
