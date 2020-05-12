package com.voting.service.results;

import com.voting.db.VoteRepository;
import com.voting.model.Option;
import com.voting.model.Vote;
import com.voting.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultsService {

    @Autowired
    private VoteRepository voteRepository;

    public Map<Option, Iterable<Vote>> getVotingResults(Voting voting) {
         return voting.getOptions()
                .stream()
                .collect(Collectors.toMap(option -> option, option -> voteRepository.findAllByOption(option)));
    }
}
