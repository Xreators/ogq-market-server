package com.ogqcorp.market.common.type.artwork;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/09 3:50 오후 Last Modified At: 2020/12/09
 */
public enum ArtworkStatus {
  REGISTERED,
  REVIEW_REQUEST,

  REVIEW,
  REVIEW_FAIL,
  PACKAGE_REQUEST,
  PACKAGE_FAIL,

  ENABLED,
  DISABLED,
  DELETED
}
