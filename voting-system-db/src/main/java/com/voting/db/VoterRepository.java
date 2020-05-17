package com.voting.db;

import com.voting.model.Voter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoterRepository extends CrudRepository<Voter, Long> {

    Optional<Voter> findByEmail(String email);

    List<Voter> findByIdIn(List<Long> userIds);

    Optional<Voter> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);
}
