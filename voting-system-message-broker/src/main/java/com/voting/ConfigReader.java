package com.voting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigReader {
	private String filename;
	private String messageBrokerIp;
	private int messageBrokerPort;
    private String messageBrokerUser;
    private String messageBrokerPwd;
    private String messageBrokerVirtualHost;
    private String exchangeName;
	
    public ConfigReader(String filename) throws FileNotFoundException{
    	this.filename = filename;
    	this.loadConfig();
    }

    public String getMessageBrokerIp() {
    	return this.messageBrokerIp;
    }
    
    public int getMessageBrokerPort() {
    	return this.messageBrokerPort;
    }
    
    public String getMessageBrokerUser() {
    	return this.messageBrokerUser;
    }
    
    public String getMessageBrokerPwd() {
    	return this.messageBrokerPwd;
    }
    
    public String getMessageBrokerVirtualHost() {
    	return this.messageBrokerVirtualHost;
    }
    
    public String getExchangeName() {
    	return this.exchangeName;
    }
    private void loadConfig() throws FileNotFoundException {
    	File file = new File(this.filename);
    	
    	messageBrokerIp = loadMessageBrokerIp(file);
    	messageBrokerPort = loadMessageBrokerPort(file);
    	messageBrokerUser = loadMessageBrokerUser(file);
    	messageBrokerPwd = loadMessageBrokerPwd(file);
    	messageBrokerVirtualHost = loadMessageBrokerVirtualHost(file);
    	exchangeName = loadExchangeName(file);
    }
    
    private String loadMessageBrokerIp(File file) throws FileNotFoundException {
    	return loadConfValue(file, "messageBrokerIp:");
    }
    
    private int loadMessageBrokerPort(File file) throws FileNotFoundException {
    	return Integer.parseInt(loadConfValue(file, "messageBrokerPort:"));
    }
    
    private String loadMessageBrokerUser(File file) throws FileNotFoundException {
    	return loadConfValue(file, "messageBrokerUser:");
    }
    
    private String loadMessageBrokerPwd(File file) throws FileNotFoundException {
    	return loadConfValue(file, "messageBrokerPwd:");
    }
    
    private String loadMessageBrokerVirtualHost(File file) throws FileNotFoundException {
    	return loadConfValue(file, "messageBrokerVirtualHost:");
    }
    
    private String loadExchangeName(File file) throws FileNotFoundException {
    	return loadConfValue(file, "exchangeName:");
    }
    
    private String loadConfValue(File file, String beggining) throws FileNotFoundException {
    	Scanner reader = new Scanner(file);
    	while (reader.hasNextLine()) {
            String data = reader.nextLine();
            if(data.startsWith(beggining)) {
                reader.close();
            	return getValueAfterColon(data);
            }
    	}
        reader.close();
    	throw new IllegalArgumentException(beggining + " not found in given config file");
    }
    
    private String getValueAfterColon(String line) {
    	return line.substring(line.lastIndexOf(":") + 1).replaceAll("\\s+","");
    }
}
