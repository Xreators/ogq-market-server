package com.ogqcorp.market.relay.service.rpc;

import com.ogqcorp.market.ontology.OntologyComm;
import com.ogqcorp.market.ontology.res.OntologyCommCodes;
import com.ogqcorp.market.relay.service.rpc.OntologyService.BatchHashes;
import com.ogqcorp.market.relay.service.rpc.dto.HashesDTO;
import com.ogqcorp.market.relay.service.rpc.error.exception.BatchAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 2:29 오후 Last Modified At: 2020/12/21
 */
@Service
public class BatchHashesImpl implements BatchHashes<HashesDTO, Void> {
  private final String BATCH_ID = "1";
  private final OntologyComm ontologyComm;

  @Autowired
  public BatchHashesImpl(OntologyComm ontologyComm) {
    this.ontologyComm = ontologyComm;
  }

  @Override
  public Mono<Void> apply(HashesDTO dto) {
    return Mono.fromCallable(() -> dto.getHashes())
      .publishOn(Schedulers.boundedElastic())
      .flatMap(it -> ontologyComm.batchAdd(BATCH_ID, it))
      .map(v -> {
        if(v.getError() == OntologyCommCodes.SUCCESS_REQ) {
          return v;
        }
        throw new BatchAddException(v.getError(), v.getDesc());
      })
      .then()
      .onErrorResume(Mono::error);
  }
}
