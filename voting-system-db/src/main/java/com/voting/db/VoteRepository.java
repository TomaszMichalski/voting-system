package com.voting.db;

import com.voting.model.OptionVoteCount;
import com.voting.model.Vote;
import com.voting.model.VoteId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {

    List<Vote> findAll();

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    @Query("SELECT NEW com.voting.model.OptionVoteCount(vote.option.id, count(vote)) FROM Vote vote "
            + "WHERE vote.option.voting.id = :votingId GROUP BY vote.option.id")
    List<OptionVoteCount> countByVotingIdGroupByOptionId(@Param("votingId") Long votingId);

    List<Vote> findByVoter_Id_AndOption_Voting_Id(Long id, Long votingId);
}

