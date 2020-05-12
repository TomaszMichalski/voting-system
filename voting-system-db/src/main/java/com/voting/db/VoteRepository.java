package com.voting.db;

import com.voting.model.Vote;
import com.voting.model.VoteId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, VoteId> {

}
