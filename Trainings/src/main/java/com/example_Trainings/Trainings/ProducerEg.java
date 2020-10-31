package com.example_Trainings.Trainings;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

public class ProducerEg {
    public static void sendMsg(String msg) throws Exception
    {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Producer<byte[]> producer = client.newProducer()
                .topic("my-first-topic")
                .create();
        producer.send(msg.getBytes());

    }
}