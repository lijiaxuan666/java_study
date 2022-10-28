package com.example.kafka.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

//自定义分区器
public class MyPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //自定义分区器，将数据中包含ljx的发送到0分区，其他发送到1分区
        String msgValue = value.toString();
        int partition;
        if (msgValue.contains("ljx"))
            partition = 0;
        else
            partition = 1;
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
