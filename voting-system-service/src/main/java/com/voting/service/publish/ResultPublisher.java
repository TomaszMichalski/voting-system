package com.voting.service.publish;

import com.voting.ResultsBroker;
import com.voting.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResultPublisher {

    @Autowired
    private ResultMapper resultMapper;

    @Autowired
    private ResultsBroker resultsBroker;

    public void publishResults(Voting voting) {
        resultsBroker.publishResults(voting.getName(), resultMapper.map(voting));
    }
}
