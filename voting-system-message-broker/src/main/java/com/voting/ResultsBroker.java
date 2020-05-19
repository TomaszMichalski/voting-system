package com.voting;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;
import java.io.BufferedReader;

public class ResultsBroker {
    public final static String messageBrokerIp = "127.0.0.1";
    public final static String messageBrokerUser = "bunny";
    public final static String messageBrokerPwd = "bunny1234";
    public final static String messageBrokerVirtualHost = "bunny_host";
    public final static String exchangeName =  "bunny_exchange";
    private final static String typePrezydenckie = "WYBORY PREZYDENCKIE 2020";
	
	public static void main(String[] args) {
		System.out.println("Hello from results broker");
    	ConnectionData connectionData = new ConnectionData(messageBrokerIp, messageBrokerUser, messageBrokerPwd, messageBrokerVirtualHost);
    	ExchangeData exchangeData = new ExchangeData(exchangeName, "fanout");
    	
        try {
        	MessageBroker messageBroker = new MessageBroker(connectionData, exchangeData);
            //TODO: change to log
            System.out.println(" [ResultsSender] Connection and channel created.");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("[Press enter to send next results] > ");
                try {
                    String messageContent = bufferedReader.readLine();
                    messageBroker.loopUserInput(typePrezydenckie);
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        }
	}
}
