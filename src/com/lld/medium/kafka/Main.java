package com.lld.medium.kafka;

import java.util.List;

public class Main {

    public static void main ( String[] args ) {
        KafkaCluster cluster = new KafkaCluster();
        Zookeeper zookeeper = new Zookeeper();

        Broker broker1 = new Broker("broker1");
        Broker broker2 = new Broker("broker2");

        cluster.addBroker(broker1);
        cluster.addBroker(broker2);

        zookeeper.registerBroker(broker1);
        zookeeper.registerBroker(broker2);


        Topic topic = new Topic("topic1");
        Partition partition1 = new Partition(0);
        Partition partition2 = new Partition(1);


        topic.addPartition(partition1);
        topic.addPartition(partition2);


        broker1.addTopic(topic);
        zookeeper.registerTopic(topic);

        EventProducer producer = new EventProducer(partition1);
        EventConsumer consumer = new EventConsumer(partition1.getEvents() ,
                                                   List.of(new Server("Server1") ,
                                                           new Server("Server2")));

        // Start consumer in a new thread
        new Thread(consumer::consumeEvents).start();

        // Produce some events
        producer.produceEvent(new Event("1", "type1", "data1"));
        producer.produceEvent(new Event("2", "type2", "data2"));

        // Give some time for the consumer to consume the events
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
