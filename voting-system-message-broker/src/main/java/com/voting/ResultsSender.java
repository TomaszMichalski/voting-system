package com.voting;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ThreadLocalRandom;


public class ResultsSender {

    public final static String messageBrokerIp = "127.0.0.1";
    public final static String messageBrokerUser = "bunny";
    public final static String messageBrokerPwd = "bunny1234";
    public final static String messageBrokerVirtualHost = "bunny_host";
    public final static String exchangeName =  "bunny_exchange";
    private final static String typePrezydenckie = "WYBORY PREZYDENCKIE 2020";

    public static void main(String[] args) {

        System.out.println(" [ResultsSender] Starting...");

        Channel channel = null;
        try {
            channel = createChannel();
            System.out.println(" [ResultsSender] Connection and channel created.");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        }

        loopUserInput(channel);
    }

    protected static void loopUserInput(Channel channel)
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        HashMap <String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Candidate 2", 0);
        headersMap.put("Candidate 2", 0);
        headersMap.put("Candidate 3", 0);
        while (true) {
        	headersMap.put("Candidate 1", (int)headersMap.get("Candidate 1") + ThreadLocalRandom.current().nextInt(0, 100));
            headersMap.put("Candidate 2", (int)headersMap.get("Candidate 2") + ThreadLocalRandom.current().nextInt(0, 100));
            headersMap.put("Candidate 3", (int)headersMap.get("Candidate 3") + ThreadLocalRandom.current().nextInt(0, 100));
            properties = properties.builder().headers(headersMap).type(typePrezydenckie).build();
            System.out.println("[Press enter to send next results] > ");
            try {
                String messageContent = bufferedReader.readLine();
                channel.basicPublish(exchangeName, "", properties, messageContent.getBytes());
            } catch (IOException e) {
            }
        }
    }

    protected static Channel createChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(messageBrokerIp);
        factory.setUsername(messageBrokerUser);
        factory.setPassword(messageBrokerPwd);
        factory.setVirtualHost(messageBrokerVirtualHost);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeName, "fanout");

        return channel;
    }
}
