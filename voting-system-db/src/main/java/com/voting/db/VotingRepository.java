package com.voting.db;

import com.voting.model.Voting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {

    List<Voting> findAll();

    @SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
    List<Voting> findByVoters_Id(Long voterId);

    Optional<Voting> findById(Long votingId);

}
