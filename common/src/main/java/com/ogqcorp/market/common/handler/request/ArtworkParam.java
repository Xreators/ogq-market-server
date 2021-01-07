package com.ogqcorp.market.common.handler.request;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/11/17 4:23 오후 Last Modified At: 2020/11/17
 */
public final class ArtworkParam {

  private ArtworkParam() {}

  public static class PATH_PARAM {
    private PATH_PARAM() {}
    public static final String ARTWORK_ID = "artworkId";
  }

  public static class QUERY_PARAM {
    private QUERY_PARAM() {}
    public static final String CATEGORY = "category";
    public static final String TYPE = "type";
  }

}
