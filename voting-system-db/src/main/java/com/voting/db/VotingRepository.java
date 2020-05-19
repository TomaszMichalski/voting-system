package com.voting.db;

import com.voting.model.Voting;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {

    Optional<Voting> findById(Long votingId);

    List<Voting> findByIdIn(List<Long> votingIds);

    List<Voting> findByIdIn(List<Long> votingIds, Sort sort);
}
