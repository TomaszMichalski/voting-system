package com.voting.db;

import com.voting.model.Voting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {

    Optional<Voting> findById(Long votingId);

}
