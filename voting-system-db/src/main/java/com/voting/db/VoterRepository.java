package com.voting.db;

import com.voting.model.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

    Optional<Voter> findByEmail(String email);

    Set<Voter> findByIdIn(List<Long> userIds);

    Boolean existsByEmail(String email);
}
