package com.voting;

public class ConnectionData {
    public String ip;
    public int port;
    public String user;
    public String pwd;
    public String virtualHost;
    
    public ConnectionData(String ip, int port, String user, String pwd, String virtualHost) {
    	this.ip = ip;
    	this.port = port;
    	this.user = user;
    	this.pwd = pwd;
    	this.virtualHost = virtualHost;
    }
}
