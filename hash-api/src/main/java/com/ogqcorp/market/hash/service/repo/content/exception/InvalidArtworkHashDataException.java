package com.ogqcorp.market.hash.service.repo.content.exception;

import static com.ogqcorp.market.common.error.ErrorCodes.BusinessErrorCodes.INVALID_ARTWORK_HASH_DATA_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 4:44 오후 Last Modified At: 2020/12/17
 */
public class InvalidArtworkHashDataException extends MarketException {

  public InvalidArtworkHashDataException(String message) {
    super(INVALID_ARTWORK_HASH_DATA_EXCEPTION, "invalid parameter(" + message + ")");
  }
}
