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


public class MessageBroker {

	public ConnectionData connectionData;
	public ExchangeData exchangeData;
	private String typePrezydenckie = "WYBORY PREZYDENCKIE 2020";
	private Channel channel;
    
    public MessageBroker(ConnectionData connectionData, ExchangeData exchangeData) {
    	this.connectionData = connectionData;
    	this.exchangeData = exchangeData;
    	channel = null;
        try {
            channel = createChannel();
            //TODO: change to log
            System.out.println(" [ResultsSender] Connection and channel created.");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        }
 
    }

    public void loopUserInput()
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        HashMap <String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("Candidate 1", 0);
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
                channel.basicPublish(exchangeData.name, "", properties, messageContent.getBytes());
            } catch (IOException e) {
            }
        }
    }

    protected Channel createChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(connectionData.ip);
        factory.setUsername(connectionData.user);
        factory.setPassword(connectionData.pwd);
        factory.setVirtualHost(connectionData.virtualHost);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(exchangeData.name, exchangeData.type);

        return channel;
    }
}
