import org.apache.pulsar.client.api.*;


public class ConsumerEg{
    public static void main(String args[]) throws Exception
    {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        /*Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("my-first-topic")
                .subscriptionName("my-first-subscription")
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();*/
        Consumer consumer = client.newConsumer()
                .topic("my-first-topic")
                .subscriptionName("my-first-subscription")
                .subscriptionType(SubscriptionType.Exclusive)
                .subscribe();

        while (true) {
            // blocks until a message is available
            Message<String> msg = consumer.receive();

            try {
                System.out.printf("Message received: %s", new String(msg.getData()));

                // Acknowledge the message so that it can be deleted by the message broker
                consumer.acknowledge(msg);
            } catch (Exception e) {
                // Message failed to process, redeliver later
                consumer.negativeAcknowledge(msg);
            }
        }
    }
}