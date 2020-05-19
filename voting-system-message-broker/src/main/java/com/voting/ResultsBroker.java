package com.voting;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class ResultsBroker {
    public final static String messageBrokerIp = "127.0.0.1";
    public final static String messageBrokerUser = "bunny";
    public final static String messageBrokerPwd = "bunny1234";
    public final static String messageBrokerVirtualHost = "bunny_host";
    public final static String exchangeName =  "bunny_exchange";
	private MessageBroker messageBroker;
    
    public ResultsBroker() throws IOException, TimeoutException {
    	this.messageBroker = new MessageBroker(
    			new ConnectionData(messageBrokerIp, messageBrokerUser, messageBrokerPwd, messageBrokerVirtualHost),
    			new ExchangeData(exchangeName, "fanout"));
    }
    
    public void publishResults(String name, HashMap<String, Object> votes) {
    	try {
        	this.messageBroker.publishEmptyMsg(name, votes);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
