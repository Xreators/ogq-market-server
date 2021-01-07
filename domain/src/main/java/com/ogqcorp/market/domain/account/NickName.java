package com.ogqcorp.market.domain.account;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.NickNamePolicy;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:29 오전 Last Modified At: 2020/12/09
 */
@Embeddable
public class NickName {
  private String value;

  public NickName() {}
  private NickName(final String value) {
    this.value = value;
  }

  public static NickName fromString(final String value) {
    return new NickName(value);
  }
  public boolean isRightNickName(NickNamePolicy policy) {
    return policy.checkRule(value) == TextCheck.PASS;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NickName)) {
      return false;
    }
    NickName nickName = (NickName) o;
    return Objects.equals(value, nickName.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

}
