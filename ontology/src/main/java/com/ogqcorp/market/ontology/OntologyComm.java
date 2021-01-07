package com.ogqcorp.market.ontology;

import com.github.ontio.OntSdk;
import com.github.ontio.account.Account;
import com.github.ontio.common.Helper;
import com.github.ontio.core.block.Block;
import com.github.ontio.core.transaction.Transaction;
import com.github.ontio.crypto.SignatureScheme;
import com.github.ontio.sdk.exception.SDKException;
import com.google.gson.Gson;
import com.ogqcorp.market.common.util.JsonConverter;
import com.ogqcorp.market.ontology.config.OntologyInitialParam;
import com.ogqcorp.market.ontology.config.OntologyKey;
import com.ogqcorp.market.ontology.error.exception.GenerateSignatureException;
import com.ogqcorp.market.ontology.error.exception.RpcInitException;
import com.ogqcorp.market.ontology.res.balance.BalanceResult;
import com.ogqcorp.market.ontology.res.batch.BatchAddResult;
import com.ogqcorp.market.ontology.res.contract.SmartContract;
import com.ogqcorp.market.ontology.res.verify.VerifyResult;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OntologyComm {
  public static final String RPC_METHOD_BATCH_ADD = "batchAdd";
  public static final String RPC_METHOD_VERIFY = "verify";

  public static final String ONTOLOGY_PUB_KEY = "pubKey";
  public static final String ONTOLOGY_HASHES_KEY = "hashes";
  public static final String ONTOLOGY_SIGNATURE_KEY = "signature";

  public static final String EXCEPTION_ERROR_KEY = "Error";
  public static final String EXCEPTION_DESC_KEY = "Desc";

  public static final String RPC_HEADER_ADDON_ID = "addonId";
  public static final String RPC_HEADER_TENANT_ID = "tenantId";

  private final Logger logger = LoggerFactory.getLogger(OntologyComm.class);

  private final OntSdk ontSdk;
  private final Account account;
  private final String walletAddress;
  private final Gson gson;

  private String contractAddress;
  private JsonRpc jsonRpc;

  public OntologyComm(String keyPath, String password, Gson gson) throws Exception {
    this.ontSdk = OntSdk.getInstance();
    this.gson = gson;

    var filePath = Paths.get(keyPath);
    var storedKey = Files.readString(filePath);
    var ontologyKey = gson.fromJson(storedKey, OntologyKey.class);
    var privateKey = Account.getGcmDecodedPrivateKey(
      ontologyKey.getKey(),
      password,
      ontologyKey.getAddress(),
      Base64.getDecoder().decode(ontologyKey.getSalt()),
      ontologyKey.getScryptN(),
      SignatureScheme.SHA256WITHECDSA
    );
    this.account = new Account(Helper.hexToBytes(privateKey), SignatureScheme.SHA256WITHECDSA);
    this.walletAddress = ontologyKey.getAddress();

    logger.info("[OntologyComm] Constructor Wallet Address : {}", walletAddress);
  }

  public void initialize(OntologyInitialParam params) throws MalformedURLException {
    logger.info("[OntologyComm] initialize() - attestationUrl : {}", params.getAttestationUrl());
    logger.info("[OntologyComm] initialize() - rpcChainNodeUrl : {}", params.getRpcChainNodeUrl());
    logger.info("[OntologyComm] initialize() - restfulChainNodeUrl : {}", params.getRestfulChainNodeUrl());
    logger.info("[OntologyComm] initialize() - contractAddress : {}", params.getContractAddress());
    logger.info("[OntologyComm] initialize() - addonId : {}", params.getAddonId());
    logger.info("[OntologyComm] initialize() - tenantId : {}", params.getTenantId());

    this.contractAddress = contractAddress;
    Map<String, String> headers = new HashMap<>();
    headers.put(RPC_HEADER_ADDON_ID, params.getAddonId());
    headers.put(RPC_HEADER_TENANT_ID, params.getTenantId());
    jsonRpc = new JsonRpc(params.getAttestationUrl(), headers);
    try {
      switch (params.getOntologyCommType()) {
        case RPC:
          ontSdk.setRpc(params.getRpcChainNodeUrl());
          ontSdk.setDefaultConnect(ontSdk.getRpc());
          break;
        default:
          ontSdk.setRestful(params.getRestfulChainNodeUrl());
          ontSdk.setDefaultConnect(ontSdk.getRestful());
          break;
      }
    } catch (SDKException e) {
      Map<String, String> errorMap = JsonConverter
          .convertObjectFromJsonStr(gson, e.getMessage(), Map.class);
      throw new RpcInitException(Integer.valueOf(errorMap.get(EXCEPTION_ERROR_KEY)), errorMap.get(EXCEPTION_DESC_KEY));
    }
  }

  public Mono<BatchAddResult> batchAdd(String id, List<String> hashes) {
    return callWithSign(id, RPC_METHOD_BATCH_ADD, hashes.stream().toArray(String[]::new))
      .map(res -> {
        logger.info("Ontology Response: {}", res);
        return JsonConverter.convertObjectFromJsonStr(gson, res, BatchAddResult.class);
      }).onErrorResume(Mono::error);
  }

  public Mono<VerifyResult> verifyHash(String id, String hash) {
    return callWithoutSign(id, RPC_METHOD_VERIFY, new String[]{hash})
      .map(res -> {
        logger.info("Ontology Response: {}", res);
        return JsonConverter.convertObjectFromJsonStr(gson, res, VerifyResult.class);
      }).onErrorResume(Mono::error);
  }

  public Flux<Object> getSmartCodeEventListByHeight(int height) {
    return Mono.fromCallable(() -> ontSdk.getConnect().getSmartCodeEvent(height))
      .map(res -> {
        List<Object> list = JsonConverter.convertObjectListFromJsonStr(gson, res.toString());
        return list;
      })
      .flatMapMany(Flux::fromIterable)
      .onErrorResume(Flux::error);
  }
  public Mono<SmartContract> getSmartCodeEventByTxHash(String txHash) {
    return Mono.fromCallable(() -> {
      Object res = ontSdk.getConnect().getSmartCodeEvent(txHash);
      return JsonConverter.convertObjectFromJsonStr(gson, res.toString(), SmartContract.class);
    }).onErrorResume(Mono::error);
  }

  public Mono<Transaction> getTransactionsByTxHash(String txHash) {
    return Mono.fromCallable(() -> ontSdk.getConnect().getTransaction(txHash)).onErrorResume(Mono::error);
  }
  public Mono<Block> getBlockByHeight(int height) {
    return Mono.fromCallable(() -> ontSdk.getConnect().getBlock(height)).onErrorResume(Mono::error);
  }
  public Mono<Block> getBlockByBlockHash(String blockHash) {
    return Mono.fromCallable(() -> ontSdk.getConnect().getBlock(blockHash)).onErrorResume(Mono::error);
  }
  public Mono<Integer> getBlockHeightByTxHash(String txHash) {
    return Mono.fromCallable(() -> ontSdk.getConnect().getBlockHeightByTxHash(txHash)).onErrorResume(Mono::error);
  }
  public Mono<BalanceResult> getBalance() {
    return Mono.fromCallable(() -> {
      Object balance = ontSdk.getConnect().getBalance(walletAddress);
      return JsonConverter.convertObjectFromJsonStr(gson, balance.toString(), BalanceResult.class);
    }).onErrorResume(Mono::error);
  }

  private Mono<String> callWithSign(String id, String method, String[] hashes) {
    Map<String, Object> params = new HashMap();
    params.put(ONTOLOGY_PUB_KEY, Helper.toHexString(account.serializePublicKey()));
    params.put(ONTOLOGY_HASHES_KEY, hashes);
    try {
      StringBuilder sb = new StringBuilder();
      for(String s : hashes) {
        sb.append(s);
      }
      params.put(
        ONTOLOGY_SIGNATURE_KEY,
        Helper.toHexString(account.generateSignature(Helper.hexToBytes(sb.toString()), account.getSignatureScheme(), null))
      );
    } catch (IndexOutOfBoundsException e) {
      throw e;
    } catch (Exception e) {
      // Map<String, String> errorMap = JSON.parseObject(e.getMessage(), Map.class);
      Map<String, String> errorMap = JsonConverter.convertObjectFromJsonStr(gson, e.getMessage(), Map.class);
      throw new GenerateSignatureException(Integer.valueOf(errorMap.get(EXCEPTION_ERROR_KEY)),
          errorMap.get(EXCEPTION_DESC_KEY));
    }
    return jsonRpc.callProcedure(id, method, params).onErrorResume(Mono::error);
  }
  private Mono<String> callWithoutSign(String id, String method, String[] hashes) {
    Map<String, Object> params = new HashMap();
    params.put(ONTOLOGY_PUB_KEY, Helper.toHexString(account.serializePublicKey()));
    params.put(ONTOLOGY_HASHES_KEY, hashes);
    return jsonRpc.callProcedure(id, method, params).onErrorResume(Mono::error);
  }

}
