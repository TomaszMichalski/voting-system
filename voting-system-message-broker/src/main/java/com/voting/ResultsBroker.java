package com.voting;

public class ResultsBroker {
    public final static String messageBrokerIp = "127.0.0.1";
    public final static String messageBrokerUser = "bunny";
    public final static String messageBrokerPwd = "bunny1234";
    public final static String messageBrokerVirtualHost = "bunny_host";
    public final static String exchangeName =  "bunny_exchange";
    private final static String typePrezydenckie = "WYBORY PREZYDENCKIE 2020";
	
	public static void main(String[] args) {
		System.out.println("Hello from results broker");
		ResultsSender resultsSender = new ResultsSender(messageBrokerIp,
				messageBrokerUser,
				messageBrokerPwd,
				messageBrokerVirtualHost,
				exchangeName,
				"fanout");
		resultsSender.loopUserInput();
	}
	
}
