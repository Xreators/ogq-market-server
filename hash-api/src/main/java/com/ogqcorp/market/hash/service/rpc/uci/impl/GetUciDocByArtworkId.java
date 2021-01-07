package com.ogqcorp.market.hash.service.rpc.uci.impl;

import com.ogqcorp.market.common.rpc.uci.response.UCIDocGetRes;
import com.ogqcorp.market.hash.interfaces.rpc.UciApiRpc;
import com.ogqcorp.market.hash.service.rpc.uci.UciDocService.GetUciDocBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/22 2:38 오후 Last Modified At: 2020/12/22
 */
@Service
public class GetUciDocByArtworkId implements GetUciDocBy<String, UCIDocGetRes> {

  private final UciApiRpc uciApiRpc;

  @Autowired
  public GetUciDocByArtworkId(UciApiRpc uciApiRpc) {
    this.uciApiRpc = uciApiRpc;
  }

  @Override
  public Mono<UCIDocGetRes> apply(String artworkId) {
    return Mono.fromCallable(() -> artworkId)
      .publishOn(Schedulers.boundedElastic())
      .flatMap(id -> uciApiRpc.getUciDoc(id))
      .onErrorResume(Mono::error);
  }
}
