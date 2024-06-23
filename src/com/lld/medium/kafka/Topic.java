package com.lld.medium.kafka;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Partition> partitions = new ArrayList<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void addPartition(Partition partition) {
        partitions.add(partition);
    }

    public Partition getPartition(int id) {
        return partitions.get(id);
    }

}
