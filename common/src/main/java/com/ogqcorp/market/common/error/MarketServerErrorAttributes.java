package com.ogqcorp.market.common.error;

import static com.ogqcorp.market.common.error.ErrorCodes.DefaultErrorCodes.HANDLER_NOT_FOUND_EXCEPTION;
import static com.ogqcorp.market.common.error.ErrorCodes.DefaultErrorCodes.UNKNOWN_SERVER_ERROR_EXCEPTION;

import com.ogqcorp.market.common.error.exception.MarketException;
import com.ogqcorp.market.common.error.exception.MarketExternalException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/07/23 3:21 오후 Last Modified At: 2020/07/23
 */
public class MarketServerErrorAttributes extends DefaultErrorAttributes {
  private final Logger logger = LoggerFactory.getLogger(MarketServerErrorAttributes.class);

  public static final String KEY_CODE = "code";
  public static final String KEY_MESSAGE = "message";
  public static final String KEY_TYPE = "type";
  public static final String VALUE_BAD_REQ = "bad_req";
  public static final String VALUE_INTERNAL = "internal";
  public static final String VALUE_NOT_FOUND = "not_found";
  public static final String KEY_STATUS = "status";

  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions errorAttributeOptions) {
    return createErrorAttributes(request, errorAttributeOptions);
  }

  public Map<String, Object> createErrorAttributes(ServerRequest serverRequest, ErrorAttributeOptions errorAttributeOptions) {
    Map<String, Object> errorAttributes = super.getErrorAttributes(serverRequest, errorAttributeOptions);
    Throwable throwable = getError(serverRequest);
    logger.error("", throwable);
    if (throwable instanceof MarketException) {
      String type = throwable instanceof MarketExternalException ? VALUE_BAD_REQ : VALUE_INTERNAL;
      errorAttributes.put(KEY_TYPE, type);
      errorAttributes.put(KEY_CODE, ((MarketException) throwable).getCode());
    } else if(isHandlerNotFound(throwable)) {
      errorAttributes.put(KEY_TYPE, VALUE_NOT_FOUND);
      errorAttributes.put(KEY_CODE, HANDLER_NOT_FOUND_EXCEPTION);
    } else {
      errorAttributes.put(KEY_TYPE, VALUE_INTERNAL);
      errorAttributes.put(KEY_CODE, UNKNOWN_SERVER_ERROR_EXCEPTION);
    }
    errorAttributes.put(KEY_MESSAGE, throwable.getMessage());
    return errorAttributes;
  }
  private boolean isHandlerNotFound(Throwable e) {
    return (e instanceof ResponseStatusException) && e.getMessage().contains("404 NOT_FOUND") && e.getMessage().contains("No matching handler");
  }
}
