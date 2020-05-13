package com.voting.service.vote;

import com.voting.db.VotingRepository;
import com.voting.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {

    @Autowired
    private VotingRepository votingRepository;

    public Iterable<Voting> listAll() {
        return votingRepository.findAll();
    }

    public void save(Voting voting) {
        votingRepository.save(voting);
    }
}
