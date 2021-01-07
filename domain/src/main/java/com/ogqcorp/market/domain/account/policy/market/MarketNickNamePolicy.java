package com.ogqcorp.market.domain.account.policy.market;

import static com.ogqcorp.market.domain.TextCheck.EMPTY_VALUE;
import static com.ogqcorp.market.domain.TextCheck.EXPR_VIOLATION;
import static com.ogqcorp.market.domain.TextCheck.PASS;
import static com.ogqcorp.market.domain.TextCheck.TOO_LONG;
import static com.ogqcorp.market.domain.TextCheck.TOO_SHORT;

import com.ogqcorp.market.domain.TextCheck;
import com.ogqcorp.market.domain.account.policy.NickNamePolicy;
import java.util.Optional;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 11:29 오전 Last Modified At: 2020/12/09
 */
public final class MarketNickNamePolicy implements NickNamePolicy {
  private static final int MIN_NICKNAME_LENGTH = 1;
  private static final int MAX_NICKNAME_LENGTH = 30;

  @Override
  public TextCheck checkRule(String text) {
    String nickname = Optional.ofNullable(text).orElseGet(() -> "").trim();
    if(nickname.isEmpty()) {
      return EMPTY_VALUE;
    }
    if(nickname.length() < MIN_NICKNAME_LENGTH) {
      return TOO_SHORT;
    }
    else if(nickname.length() > MAX_NICKNAME_LENGTH) {
      return TOO_LONG;
    }
    return containSpecialChar(nickname) ? EXPR_VIOLATION : PASS;
  }
  private boolean containSpecialChar(String src) {
    return Optional.ofNullable(src).map(s -> {
      for(char c : s.toCharArray()) {
        if (!Character.isLetterOrDigit(c)) {
          return true;
        }
      }
      return false;
    }).orElse(false);
  }
}
