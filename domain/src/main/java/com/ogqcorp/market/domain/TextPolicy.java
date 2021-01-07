package com.ogqcorp.market.domain;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 10:45 오전 Last Modified At: 2020/12/09
 */
@FunctionalInterface
public interface TextPolicy {
  TextCheck checkRule(String text);
}
