package com.ogqcorp.market.relay.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ogqcorp.market.ontology.OntologyComm;
import com.ogqcorp.market.ontology.config.OntologyCfgParam;
import com.ogqcorp.market.ontology.config.OntologyInitialParam;
import com.ogqcorp.market.ontology.config.OntologyInitialParam.OntologyCommType;
import com.ogqcorp.market.ontology.res.batch.BatchAddResult;
import com.ogqcorp.market.ontology.res.batch.BatchAddResultTypeAdapter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by OGQ Corp. User: iseheon Created At: 2020/12/21 1:51 오후 Last Modified At: 2020/12/21
 */
@Configuration
public class RelayContextConfig {

  @Value("${ontology.account.path}")
  private String wKeyPath;
  @Value("${ontology.account.password}")
  private String wKeyPassword;

  @Value("${ontology.config}")
  private String configPath;
  @Value("${ontology.rpc-chain-node-url}")
  private String onRpcChainNodeUrl;
  @Value("${ontology.restful-chain-node-url}")
  private String onRestfulChainNodeUrl;
  @Value("${ontology.comm-type}")
  private OntologyCommType ontologyCommType;

  @Bean
  public Gson getGson() {
    return new GsonBuilder().registerTypeAdapter(BatchAddResult.class, new BatchAddResultTypeAdapter()).create();
  }

  @Bean
  public OntologyComm ontologyComm() throws Exception {
    OntologyComm ontologyComm = new OntologyComm(wKeyPath, wKeyPassword, getGson());
    OntologyCfgParam ontologyCfgParam = getGson().fromJson(readFile(configPath), OntologyCfgParam.class);
    ontologyComm.initialize(OntologyInitialParam.builder()
      .attestationUrl(ontologyCfgParam.getUrl())
      .rpcChainNodeUrl(onRpcChainNodeUrl)
      .restfulChainNodeUrl(onRestfulChainNodeUrl)
      .contractAddress(ontologyCfgParam.getContract_address())
      .addonId(ontologyCfgParam.getAddon_id())
      .tenantId(ontologyCfgParam.getTenant_id())
      .ontologyCommType(ontologyCommType)
      .build());
    return ontologyComm;
  }
  private String readFile(String path) throws IOException {
    var filePath = Paths.get(path);
    return Files.readString(filePath);
  }
}
