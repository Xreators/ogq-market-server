package com.ogqcorp.market.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/17 2:34 오후 Last Modified At: 2020/12/17
 */
public final class JsonConverter {

  public static <T> List<T> convertObjectListFromJsonStr(Gson gson, String src) {
    return gson.fromJson(src, new TypeToken<List<T>>(){}.getType());
  }
  public static <T> T convertObjectFromJsonStr(Gson gson, String src, Class<T> clazz) {
    return gson.fromJson(src, clazz);
  }
  public static String convertJsonStringFromObject(Gson gson, Object obj) {
    return gson.toJson(obj);
  }
}

