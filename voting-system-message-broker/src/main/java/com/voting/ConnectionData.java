package com.voting;

public class ConnectionData {
    public String ip;
    public String user;
    public String pwd;
    public String virtualHost;
    
    public ConnectionData(String ip, String user, String pwd, String virtualHost) {
    	this.ip = ip;
    	this.user = user;
    	this.pwd = pwd;
    	this.virtualHost = virtualHost;
    }
}
