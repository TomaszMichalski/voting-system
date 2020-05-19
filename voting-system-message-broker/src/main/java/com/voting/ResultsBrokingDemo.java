package com.voting;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeoutException;

public class ResultsBrokingDemo {
	private static final int pauseDuration = 1500;
	
	public static void main(String[] args) throws InterruptedException {
		ResultsBroker resultsBroker;
		try {
			resultsBroker = new ResultsBroker();
		} catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        }

        HashMap <String, Object> votes = new HashMap<String, Object>();
        votes.put("Candidate 1", 0);
        votes.put("Candidate 2", 0);
        votes.put("Candidate 3", 0);
        votes.put("Candidate 4", 0);
        votes.put("Candidate 5", 0);
        while (true) {
        		Thread.sleep(pauseDuration);
            	votes.put("Candidate 2", (int)votes.get("Candidate 2") + ThreadLocalRandom.current().nextInt(0, 38));
                votes.put("Candidate 4", (int)votes.get("Candidate 4") + ThreadLocalRandom.current().nextInt(0, 34));
                votes.put("Candidate 1", (int)votes.get("Candidate 1") + ThreadLocalRandom.current().nextInt(0, 14));
                votes.put("Candidate 3", (int)votes.get("Candidate 3") + ThreadLocalRandom.current().nextInt(0, 8));
                votes.put("Candidate 5", (int)votes.get("Candidate 5") + ThreadLocalRandom.current().nextInt(0, 6));
                
                resultsBroker.publishResults("WYBORY PREZYDENCKIE 2020", votes);
        }

	}
}
