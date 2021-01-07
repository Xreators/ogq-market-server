package com.ogqcorp.market.domain.account;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.EmailPolicy;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 10:41 오전 Last Modified At: 2020/12/09
 */
@Embeddable
public class Email {
  private String value;

  public Email() {}
  private Email(final String value) {
    this.value = value;
  }
  public static Email fromString(final String value) {
    return new Email(value);
  }
  public boolean isRightEmail(EmailPolicy policy) {
    return policy.checkRule(value) == TextCheck.PASS;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Email)) {
      return false;
    }
    Email email = (Email) o;
    return Objects.equals(value, email.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
