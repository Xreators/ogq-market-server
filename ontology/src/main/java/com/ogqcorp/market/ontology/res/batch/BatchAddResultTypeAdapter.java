package com.ogqcorp.market.ontology.res.batch;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/05/28 4:37 오후 Last Modified At: 2020/05/28
 */
public class BatchAddResultTypeAdapter implements JsonDeserializer<BatchAddResult> {
  public BatchAddResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) {
    BatchAddResult batchAddResult = new BatchAddResult();

    for (Entry<String, JsonElement> e : json.getAsJsonObject().entrySet()) {
      if ("result".equals(e.getKey())) {
        List<String> result = new ArrayList<>();
        if (e.getValue().isJsonArray()) {
          for(JsonElement r: e.getValue().getAsJsonArray()) {
            result.add(r.getAsString());
          }
        } else {
          result.add(e.getValue().getAsString());
        }
        batchAddResult.setResult(result);
      } else if("desc".equals(e.getKey())) {
        batchAddResult.setDesc(e.getValue().getAsString());
      } else if("error".equals(e.getKey())) {
        batchAddResult.setError(e.getValue().getAsInt());
      } else if("id".equals(e.getKey())) {
        batchAddResult.setId(e.getValue().getAsString());
      } else if("jsonrpc".equals(e.getKey())) {
        batchAddResult.setJsonrpc(e.getValue().getAsString());
      }
    }
    return batchAddResult;
  }
}
