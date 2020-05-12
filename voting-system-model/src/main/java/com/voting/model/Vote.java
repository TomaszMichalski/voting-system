package com.voting.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Vote {

    @EmbeddedId
    private VoteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("voterId")
    private Voter voter;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("optionId")
    private Option option;

    private LocalDateTime dateTime;

    public Vote() {
        //
    }

    public Vote(Voter voter, Option option, LocalDateTime dateTime) {
        this.id = new VoteId(voter.getId(), option.getId());
        this.voter = voter;
        this.option = option;
        this.dateTime = dateTime;
    }

    public VoteId getId() {
        return id;
    }

    public void setId(VoteId id) {
        this.id = id;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(voter, vote.voter) &&
                Objects.equals(option, vote.option) &&
                Objects.equals(dateTime, vote.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voter, option, dateTime);
    }
}
