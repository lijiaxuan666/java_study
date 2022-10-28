package com.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class KafkaApplicationTests {

    //生产消息，异步调用
    @Test
    void CustomProducer() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //使用自定义分区器，参数为自定义分区器的全限定类名
        //properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.example.kafka.config.MyPartitioner");

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.发送数据
        kafkaProducer.send(new ProducerRecord<>("article","hello"));
        //3.关闭资源
        kafkaProducer.close();

    }

    //生产消息，同步调用
    @Test
    void CustomProducerSync() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.发送数据
        try {
            //在异步后面加上一个.get()方法，就可以变为同步
            kafkaProducer.send(new ProducerRecord<>("article","hello")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //3.关闭资源
        kafkaProducer.close();
    }

    //带回调生产消息
    @Test
    void CustomProducerCallback() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.发送数据
        kafkaProducer.send(new ProducerRecord<>("article", "hello"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                //如果没有异常，则打印主题分区
                if(exception==null){
                    System.out.println("主题:"+metadata.topic()+"分区:"+metadata.partition());
                }
            }
        });
        //3.关闭资源
        kafkaProducer.close();
    }

    //带回调向指定分区生产消息
    @Test
    void CustomProducerCallbackPartitions() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.向指定分区发送数据
        kafkaProducer.send(new ProducerRecord<>("article",0, "","hello"), new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                //如果没有异常，则打印主题分区
                if(exception==null){
                    System.out.println("主题:"+metadata.topic()+"分区:"+metadata.partition());
                }
            }
        });
        //3.关闭资源
        kafkaProducer.close();
    }

    //发送消息设置参数
    @Test
    void CustomProducerParameters() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //缓冲区大小，默认为32m
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        //批次大小，默认为16k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG,16384);
        //等待时间，默认为0ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG,1);
        //压缩数据，默认为none，可配置gzip、snappy、lz4、zstd，其中snappy较为常用
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.向指定分区发送数据
        kafkaProducer.send(new ProducerRecord<>("article","hello"));
        //3.关闭资源
        kafkaProducer.close();
    }

    //ack应答级别
    @Test
    void CustomProducerCallbackAck() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //acks应答级别设置
        properties.put(ProducerConfig.ACKS_CONFIG,"1");
        //设置最大重试次数，默认为int最大值2147483647
        properties.put(ProducerConfig.RETRIES_CONFIG,10);

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.向指定分区发送数据
        kafkaProducer.send(new ProducerRecord<>("article","hello"));
        //3.关闭资源
        kafkaProducer.close();
    }

    //事务
    @Test
    void CustomProducerTransaction() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的序列化类型
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        //指定事务id，不然会报错，id随便起，唯一就行
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"transaction_id_01");

        //1.创建kafka生产者对象
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer<>(properties);
        //2.初始化事务
        kafkaProducer.initTransactions();
        //3.开启事务
        kafkaProducer.beginTransaction();
        try{
            //4.向指定分区发送数据
            kafkaProducer.send(new ProducerRecord<>("article","hello"));
            //5.提交事务
            kafkaProducer.commitTransaction();
        }catch (Exception e){
            //若出现异常则终止事务
            kafkaProducer.abortTransaction();
        }finally {
            //6.关闭资源
            kafkaProducer.close();
        }
    }


    /**消费者
     *
     */
    //消费者消费指定主题信息
    @Test
    void CustomConsumer() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的反序列化类型
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        //指定groupId（必须！！！！）
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");

        //1.创建kafka消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //2.订阅主题
        ArrayList<String> topics = new ArrayList<>();
        topics.add("article");
        kafkaConsumer.subscribe(topics);

        boolean flag=false;
        //3.循环打印消费的数据
        while(true){
            //可以加入一个跳出循环的条件，可以通过其他线程修改flag的值来控制循环结束
            //if(flag)
            //    break;
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
        }
    }

    //消费者消费指定分区主题
    @Test
    void CustomConsumerPartition() {
        Properties properties=new Properties();
        //连接集群，这里也可以指定一个或者多个kfaka，指定多个可以防止某台服务器挂掉导致连接失败
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.196.138:9092,192.168.196.91:9092");
        //指定key和value的反序列化类型
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        //指定groupId（必须！！！！）
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"test");

        //1.创建kafka消费者对象
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //2.订阅主题
        ArrayList<TopicPartition> topics = new ArrayList<>();
        topics.add(new TopicPartition("article",0));
        kafkaConsumer.assign(topics);

        boolean flag=false;
        //3.循环打印消费的数据
        while(true){
            //可以加入一个跳出循环的条件，可以通过其他线程修改flag的值来控制循环结束
            //if(flag)
            //    break;
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
        }
    }

}

