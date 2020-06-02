package com.voting;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;
import java.io.FileNotFoundException;

public class ResultsBroker {
	private MessageBroker messageBroker;
    private String confFilename; 
	
    public ResultsBroker(String confFile) throws IOException, TimeoutException, FileNotFoundException {
    	this.confFilename = confFilename;
    	ConfigReader configReader = new ConfigReader(confFile);
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
