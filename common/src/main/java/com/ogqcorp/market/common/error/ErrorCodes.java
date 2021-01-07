package com.ogqcorp.market.common.error;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/07 2:50 오후 Last Modified At: 2020/12/07
 */
public final class ErrorCodes {

  private ErrorCodes() {}
  public final class DefaultErrorCodes {
    public static final int BAD_PARAMETER_EXCEPTION = 400000;
    public static final int NO_AUTHENTICATED_EXCEPTION = 401000;
    public static final int NO_AUTHORIZE_EXCEPTION = 403000;
    public static final int HANDLER_NOT_FOUND_EXCEPTION = 404000;
    public static final int UNKNOWN_SERVER_ERROR_EXCEPTION = 500000;
  }
  public final class DomainErrorCodes { // domain, service(repository) layer
    public static final int NOT_FOUND_CREATOR_EXCEPTION = 404001;
    public static final int NOT_FOUND_ARTWORK_EXCEPTION = 404002;
    public static final int DUPLICATE_ARTWORK_HASH_EXCEPTION = 404005;
    public static final int NOT_FOUND_UCI_DOCUMENT_EXCEPTION = 404006;
    public static final int NOT_FOUND_CERTIFICATION_REQUEST_EXCEPTION = 404007;
    public static final int NOT_FOUND_ARTWORK_HASH_DATA_EXCEPTION = 404008;
  }
  public final class BusinessErrorCodes { // usecase layer
    public static final int NOT_ALLOW_ARTWORK_UPLOAD = 400003;
    public static final int INVALID_ARTWORK_HASH_DATA_EXCEPTION = 400004;
  }
  public final class ApplicationErrorCodes { // service(storage), service(amqp)
    public static final int FILE_HASH_CREATE_FAILED_EXCEPTION = 500004;
  }
  public final class InfrastructureErrorCodes { // infrastructure layer
    public static final int QUEUE_CHANNEL_CONNECT_EXCEPTION = 500001;
    public static final int QUEUE_PAYLOAD_IS_NULL_EXCEPTION = 500002;
    public static final int STOCK_IMAGE_HASH_QUEUE_IS_FULL_EXCEPTION = 500003;
    public static final int RPC_COMM_EXCEPTION = 500005;
  }
}
