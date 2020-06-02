package com.voting;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

public class ResultsBrokingDemo {
	
	private static final String CONF_FILE = "src/main/java/com/voting/.conf";
	
	public static void main(String[] args) throws InterruptedException {
		ResultsBroker resultsBroker;
		try {
			resultsBroker = new ResultsBroker(CONF_FILE);
		} catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        }

        HashMap <String, Object> votes2 = new HashMap<String, Object>();
        votes2.put("Candidate 1", 0);
        votes2.put("Candidate 2", 0);
        
        HashMap <String, Object> votes5 = new HashMap<String, Object>();
        votes5.put("Name1", 0);
        votes5.put("Name2", 0);
        votes5.put("Name3", 0);
        votes5.put("Name4", 0);
        votes5.put("Name5", 0);
        
        HashMap <String, Object> votes8 = new HashMap<String, Object>();
        votes8.put("A", 0);
        votes8.put("B", 0);
        votes8.put("C", 0);
        votes8.put("D", 0);
        votes8.put("E", 0);
        votes8.put("F", 0);
        votes8.put("G", 0);
        votes8.put("H", 0);
        
        while (true) {
        		int pauseDuration = ThreadLocalRandom.current().nextInt(300, 1500);
        		Thread.sleep(pauseDuration);
                
                if(pauseDuration % 3 == 0) {
                	votes2.put("Candidate 1", (int)votes2.get("Candidate 1") + ThreadLocalRandom.current().nextInt(0, 50));
                    votes2.put("Candidate 2", (int)votes2.get("Candidate 2") + ThreadLocalRandom.current().nextInt(0, 51));
                	resultsBroker.publishResults("votings2", votes2);
                } else if(pauseDuration % 3 == 1){
                	votes5.put("Name2", (int)votes5.get("Name2") + ThreadLocalRandom.current().nextInt(0, 38));
                    votes5.put("Name4", (int)votes5.get("Name4") + ThreadLocalRandom.current().nextInt(0, 34));
                    votes5.put("Name1", (int)votes5.get("Name1") + ThreadLocalRandom.current().nextInt(0, 14));
                    votes5.put("Name3", (int)votes5.get("Name3") + ThreadLocalRandom.current().nextInt(0, 8));
                    votes5.put("Name5", (int)votes5.get("Name5") + ThreadLocalRandom.current().nextInt(0, 6));
                	resultsBroker.publishResults("votings5", votes5);
                } else {
                	votes8.put("A", (int)votes8.get("A") + ThreadLocalRandom.current().nextInt(0, 2));
                    votes8.put("B", (int)votes8.get("B") + ThreadLocalRandom.current().nextInt(0, 8));
                    votes8.put("C", (int)votes8.get("C") + ThreadLocalRandom.current().nextInt(0, 16));
                    votes8.put("D", (int)votes8.get("D") + ThreadLocalRandom.current().nextInt(0, 9));
                    votes8.put("E", (int)votes8.get("E") + ThreadLocalRandom.current().nextInt(0, 6));
                    votes8.put("F", (int)votes8.get("F") + ThreadLocalRandom.current().nextInt(0, 30));
                    votes8.put("G", (int)votes8.get("G") + ThreadLocalRandom.current().nextInt(0, 3));
                    votes8.put("H", (int)votes8.get("H") + ThreadLocalRandom.current().nextInt(0, 26));
                	resultsBroker.publishResults("votings8", votes8);
                }
        }

	}
}
