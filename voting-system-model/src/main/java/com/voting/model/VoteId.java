package com.voting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class VoteId implements Serializable {

    @NonNull
    @Column(name = "voter_id")
    private Long voterId;

    @NonNull
    @Column(name = "option_id")
    private Long optionId;

    @NonNull
    @Column(name = "voting_id")
    private Long votingId;

}
