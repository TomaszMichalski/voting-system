package com.voting.service.publish;

import com.voting.db.VoteRepository;
import com.voting.model.Option;
import com.voting.model.OptionVoteCount;
import com.voting.model.Voting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    @Autowired
    private VoteRepository voteRepository;

    public Map<String, Object> map(Voting voting) {
        List<OptionVoteCount> voteCounts = voteRepository.countByVotingIdGroupByOptionId(voting.getId());
        return toVotingResults(voting, voteCounts);
    }

    public Map<String, Object> toVotingResults(Voting voting, List<OptionVoteCount> voteCounts) {
        return voting.getOptions()
                .stream()
                .collect(Collectors.toMap(Option::getName,
                        option -> getOptionVoteCount(option, voteCounts)));
    }

    public Object getOptionVoteCount(Option option, List<OptionVoteCount> voteCounts) {
        return voteCounts.stream()
                .filter(voteCount -> voteCount.getOptionId().equals(option.getId()))
                .findFirst()
                .map(OptionVoteCount::getVoteCount)
                .orElse(0L);
    }
}
