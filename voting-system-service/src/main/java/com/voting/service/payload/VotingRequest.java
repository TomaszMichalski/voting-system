package com.voting.service.payload;

import com.voting.service.validator.ValidDateRange;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@ValidDateRange(start = "start", end = "end")
public class VotingRequest {

    @NotBlank
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime start;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime end;

    @NotNull
    private Boolean singleChoice;

    @NotEmpty
    @Valid
    private List<OptionRequest> options;

    @NotEmpty
    private List<Long> voterIds;

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isSingleChoice() {
        return singleChoice;
    }

    public List<OptionRequest> getOptions() {
        return options;
    }

    public List<Long> getVoterIds() {
        return voterIds;
    }

}
