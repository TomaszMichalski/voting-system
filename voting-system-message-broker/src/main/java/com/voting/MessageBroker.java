package com.voting;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class MessageBroker {

	private ExchangeData exchangeData;
	private Channel channel;
    
    public MessageBroker(ConnectionData connectionData, ExchangeData exchangeData) throws IOException, TimeoutException {
    	this.exchangeData = exchangeData;
    	this.channel = createChannel(connectionData);
    }
    
    public void publishEmptyMsg(String msgType, HashMap<String, Object> headersMap) throws IOException {
        AMQP.BasicProperties properties = new AMQP.BasicProperties();
        properties = properties.builder().headers(headersMap).type(msgType).build();
        channel.basicPublish(exchangeData.name, "", properties, null);
    }
    
    private Channel createChannel(ConnectionData connectionData) throws IOException, TimeoutException {
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
