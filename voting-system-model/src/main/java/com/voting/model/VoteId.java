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

    public VoteId() {
        //
    }

    public VoteId(Long voterId, Long optionId) {
        this.voterId = voterId;
        this.optionId = optionId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteId voteId = (VoteId) o;
        return Objects.equals(voterId, voteId.voterId) &&
                Objects.equals(optionId, voteId.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterId, optionId);
    }
}
