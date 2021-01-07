package com.ogqcorp.market.domain.content.uci;

import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public final class UCICode {
    private String value;

    public UCICode(final String value) {
        this.value = value;
    }
    public static UCICode fromString(String code) { return new UCICode(code); }

    public String idString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UCICode)) return false;
        UCICode uciCode = (UCICode) o;
        return Objects.equals(value, uciCode.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }

    public UCICode() {
        // Needed by Hibernate
    }
}
