package com.ogqcorp.market.common.amqp.msg;

import org.apache.logging.log4j.util.Strings;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/16 2:08 오후 Last Modified At: 2020/12/16
 */
public class EmptyAMQPMsg implements MarketAMQPMsg {
  private String data = Strings.EMPTY;

  public String getData() {
    return data;
  }
}
