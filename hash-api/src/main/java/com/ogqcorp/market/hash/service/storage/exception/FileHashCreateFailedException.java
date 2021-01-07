package com.ogqcorp.market.hash.service.storage.exception;

import static com.ogqcorp.market.common.error.ErrorCodes.ApplicationErrorCodes.FILE_HASH_CREATE_FAILED_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 3:14 오후 Last Modified At: 2020/12/17
 */
public class FileHashCreateFailedException extends MarketException {

  public FileHashCreateFailedException(String message) {
    super(FILE_HASH_CREATE_FAILED_EXCEPTION, "input stream read fail: (" + message + ")");
  }
}
