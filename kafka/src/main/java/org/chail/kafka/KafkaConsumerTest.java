package org.chail.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

public class KafkaConsumerTest implements Runnable {

    private final KafkaConsumer<String, String> consumer;
    private ConsumerRecords<String, String> msgList;
    private final String topic;
    private static final String GROUPID = "test-utils"+ UUID.randomUUID().toString();

    public KafkaConsumerTest(String bootstrap,String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrap);
        props.put("group.id", GROUPID);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        this.consumer = new KafkaConsumer<String, String>(props);
        this.topic = topicName;
        this.consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        int messageNo = 1;
        //System.out.println("---------开始消费---------");
        try {
            for (; ; ) {
                msgList = consumer.poll(1000);
                if (null != msgList && msgList.count() > 0) {
                    for (ConsumerRecord<String, String> record : msgList) {
                        String key = record.key();
                        String value = record.value();
                        long offset = record.offset();
                        long timestamp = record.timestamp();
                        JSONObject json=new JSONObject();
                        json.put("key",key);
                        json.put("value",value);
                        json.put("offset",offset);
                        json.put("timestamp",timestamp);
                        System.out.println(json.toString());
                        messageNo++;
                    }
                } else {
                    //System.out.println("---------等待1秒---------"+messageNo);
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    public static void main(String args[]) {
        if(args.length==2){
            String bootstrap = args[0];
            String topicName = args[1];
            KafkaConsumerTest test1 = new KafkaConsumerTest(bootstrap,topicName);
            Thread thread1 = new Thread(test1);
            thread1.start();
        }else{
            System.out.println("参数错误");
        }
    }
}