package com.voting.service.payload;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class OptionRequest {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionRequest that = (OptionRequest) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
