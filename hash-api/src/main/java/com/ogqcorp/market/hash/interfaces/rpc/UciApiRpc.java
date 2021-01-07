package com.ogqcorp.market.hash.interfaces.rpc;

import com.ogqcorp.market.common.handler.request.ArtworkParam.PATH_PARAM;
import com.ogqcorp.market.common.rpc.uci.response.UCIDocGetRes;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import reactor.core.publisher.Mono;

@FeignClient
public interface UciApiRpc {
  @RequestLine("GET /rpc/uci-code/{" + PATH_PARAM.ARTWORK_ID + "}")
  @Headers("Content-Type: application/json")
  Mono<UCIDocGetRes> getUciDoc(@Param(PATH_PARAM.ARTWORK_ID) String artworkId);
}
