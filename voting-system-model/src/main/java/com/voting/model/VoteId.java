package com.voting.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoteId implements Serializable {

    @Column(name = "voter_id")
    private Long voterId;

    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "voting_id")
    private Long votingId;

    public VoteId() {
        //
    }

    public VoteId(Long voterId, Long optionId, Long votingId) {
        this.voterId = voterId;
        this.optionId = optionId;
        this.votingId = votingId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Long getVotingId() {
        return votingId;
    }

    public void setVotingId(Long votingId) {
        this.votingId = votingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteId voteId = (VoteId) o;
        return Objects.equals(voterId, voteId.voterId) &&
                Objects.equals(optionId, voteId.optionId) &&
                Objects.equals(votingId, voteId.votingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterId, optionId, votingId);
    }
}
