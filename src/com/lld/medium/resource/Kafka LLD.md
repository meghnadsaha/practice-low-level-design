## Low-Level Design: Kafka

To design a system to notify events to multiple servers, similar to Kafka, we'll follow these steps:


### Extended Core Components

1. **KafkaCluster**: Manages the overall Kafka cluster.
2. **Broker**: Represents a Kafka broker within the cluster.
3. **Topic**: Represents a topic that events are published to.
4. **Partition**: Represents a partition within a topic.
5. **Zookeeper**: Manages the metadata for the Kafka cluster.

### Class Diagram

Here's the extended class diagram to represent these components:

Here's the extended class diagram, including Kafka cluster, broker, topic, partition, and Zookeeper components:

```plantuml
@startuml

class Event {
  -id: String
  -type: String
  -data: String
  +getId(): String
  +getType(): String
  +getData(): String
}

class EventQueue {
  -queue: LinkedList<Event>
  +addEvent(event: Event): void
  +getNextEvent(): Event
}

class EventProducer {
  -partition: Partition
  +produceEvent(event: Event): void
}

class EventConsumer {
  -queue: EventQueue
  -servers: List<Server>
  +consumeEvents(): void
}

class Server {
  -id: String
  +notify(event: Event): void
}

class KafkaCluster {
  -brokers: List<Broker>
  +addBroker(broker: Broker): void
  +getBroker(id: String): Broker
}

class Broker {
  -id: String
  -topics: Map<String, Topic>
  +addTopic(topic: Topic): void
  +getTopic(name: String): Topic
}

class Topic {
  -name: String
  -partitions: List<Partition>
  +addPartition(partition: Partition): void
  +getPartition(id: int): Partition
}

class Partition {
  -id: int
  -events: EventQueue
  +addEvent(event: Event): void
  +getNextEvent(): Event
}

class Zookeeper {
  -metadata: Map<String, Object>
  +registerBroker(broker: Broker): void
  +registerTopic(topic: Topic): void
  +getMetadata(key: String): Object
}

Event --> EventQueue : adds
EventQueue "1" *-- "many" Event : contains
EventProducer --> Partition : produces to
Partition --> EventQueue : contains
EventConsumer --> EventQueue : consumes from
EventConsumer --> Server : notifies
Server "many" <|-- EventConsumer : notifies

KafkaCluster "1" *-- "many" Broker : contains
Broker "1" *-- "many" Topic : contains
Topic "1" *-- "many" Partition : contains

Zookeeper --> KafkaCluster : manages
Zookeeper --> Broker : registers
Zookeeper --> Topic : registers

@enduml
```

This class diagram includes:

- **Event**: Represents an event to be notified.
- **EventQueue**: Manages the queue of events to be dispatched.
- **EventProducer**: Produces events and sends them to a specific partition.
- **EventConsumer**: Consumes events from the event queue and notifies servers.
- **Server**: Represents a server that will receive notifications.
- **KafkaCluster**: Manages the overall Kafka cluster.
- **Broker**: Represents a Kafka broker within the cluster.
- **Topic**: Represents a topic that events are published to.
- **Partition**: Represents a partition within a topic.
- **Zookeeper**: Manages the metadata for the Kafka cluster.

### Sequence Diagram

Here's the sequence diagram showing the interactions including Kafka components:

```plantuml
@startuml

actor Producer

Producer -> EventProducer: produceEvent(event)
EventProducer -> Partition: addEvent(event)
Partition -> EventQueue: addEvent(event)

loop Continuously
  EventConsumer -> EventQueue: getNextEvent()
  EventQueue -> EventConsumer: return event
  EventConsumer -> Server: notify(event)
end

@enduml
```

[Full Code](https://github.com/meghnadsaha/practice-low-level-design/tree/master/src/com/lld/medium/kafka)


### Class Diagram
<img src="https://raw.githubusercontent.com/meghnadsaha/practice-low-level-design/master/src/com/lld/medium/resource/kafka-Class%20Diagram.png"/>

### Activity Diagram
<img src="https://raw.githubusercontent.com/meghnadsaha/practice-low-level-design/master/src/com/lld/medium/resource/kafka-Sequence%20Diagram.png"/>
