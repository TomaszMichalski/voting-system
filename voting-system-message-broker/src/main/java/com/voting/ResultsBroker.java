package com.voting;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.io.FileNotFoundException;

public class ResultsBroker {
	private MessageBroker messageBroker;
    private String confFilename;
    private final String DEFAULT_CONFIG = "../voting-system-message-broker/src/main/java/com/voting/.conf";
	
    public ResultsBroker() throws IOException, TimeoutException, FileNotFoundException {
    	this.confFilename = DEFAULT_CONFIG;
    	ConfigReader configReader = new ConfigReader(confFilename);
    	this.messageBroker = new MessageBroker(
    			new ConnectionData(configReader.getMessageBrokerIp(),
    					configReader.getMessageBrokerUser(), 
    					configReader.getMessageBrokerPwd(), 
    					configReader.getMessageBrokerVirtualHost()),
    			new ExchangeData(configReader.getExchangeName(), "fanout"));
    }
    
    public ResultsBroker(String confFilename) throws IOException, TimeoutException, FileNotFoundException {
    	this.confFilename = confFilename;
    	ConfigReader configReader = new ConfigReader(confFilename);
    	this.messageBroker = new MessageBroker(
    			new ConnectionData(configReader.getMessageBrokerIp(),
    					configReader.getMessageBrokerUser(), 
    					configReader.getMessageBrokerPwd(), 
    					configReader.getMessageBrokerVirtualHost()),
    			new ExchangeData(configReader.getExchangeName(), "fanout"));
    }
    
    public void publishResults(String name, HashMap<String, Object> votes) {
    	try {
        	this.messageBroker.publishEmptyMsg(name, votes);
        	System.out.println("Results send! Voting:" + name + ", results: " + votes.toString());
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
}
