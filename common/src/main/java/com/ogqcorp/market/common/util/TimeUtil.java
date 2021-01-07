package com.ogqcorp.market.common.util;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/10 10:30 오전 Last Modified At: 2020/12/10
 */
public class TimeUtil {
  private static final String SEOUL_ZONE = "Asia/Seoul";
  private static final String YMDT_FORMAT = "yyyyMMddHHmmss";
  private static final String YMDT_DASH_FORMAT = "yyyy-MM-dd HH:mm:ss";

  private TimeUtil() {}

  public static ZonedDateTime utcNow() {
    return ZonedDateTime.now(ZoneOffset.UTC);
  }

  public static ZonedDateTime fromEpochSecond(long secs) {
    Instant i = Instant.ofEpochSecond(secs);
    return ZonedDateTime.ofInstant(i, ZoneOffset.UTC);
  }
}
