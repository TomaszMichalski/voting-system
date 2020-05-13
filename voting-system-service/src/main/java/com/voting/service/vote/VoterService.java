package com.voting.service.vote;

import com.voting.db.VoterRepository;
import com.voting.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoterService {

    @Autowired
    private VoterRepository voterRepository;

    public Iterable<Voter> listAll() {
        return voterRepository.findAll();
    }
}
