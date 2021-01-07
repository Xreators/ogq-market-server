package com.ogqcorp.market.domain.account;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.UserNamePolicy;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:29 오전 Last Modified At: 2020/12/09
 */
@Embeddable
public class UserName {
  private String value;

  public UserName() {}
  private UserName(final String value) {
    this.value = value;
  }

  public static UserName fromString(final String value) {
    return new UserName(value);
  }
  public boolean isRightUserName(UserNamePolicy policy) {
    return policy.checkRule(value) == TextCheck.PASS;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserName)) {
      return false;
    }
    UserName userName = (UserName) o;
    return Objects.equals(value, userName.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
