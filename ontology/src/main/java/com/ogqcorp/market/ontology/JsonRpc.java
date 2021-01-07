package com.ogqcorp.market.ontology;

import com.alibaba.fastjson.JSON;
import com.github.ontio.common.ErrorCode;
import com.ogqcorp.market.ontology.error.exception.RpcCommException;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

public class JsonRpc {
  private final Logger logger = LoggerFactory.getLogger(JsonRpc.class);

  private static final String RPC_TYPE_KEY = "jsonrpc";
  private static final String METHOD_KEY = "method";
  private static final String PARAMS_KEY = "params";
  private static final String ID_KEY = "id";

  private final String UTF8_CHARSET = "UTF-8";
  public final String JSON_RPC_VERSION = "2.0";

  private static final int TIMEOUT_MILLISECONDS = 30000;

  private URL url;
  private WebClient webClient;
  private Map<String, String> headers;

  public JsonRpc(String url) throws MalformedURLException {
    initialize(url, new HashMap<>());
  }
  public JsonRpc(String url, Map<String, String> customHeaders) throws MalformedURLException {
    initialize(url, customHeaders);
  }
  private void initialize(String url, Map<String, String> customHeaders) throws MalformedURLException {
    this.url = new URL(url);
    this.headers = customHeaders;
    TcpClient tcpClient = TcpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MILLISECONDS)
        .doOnConnected(connection -> {
          connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS));
          connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS));
        });
    this.webClient = WebClient.builder()
      .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
      .baseUrl(url)
      .defaultHeaders(header -> {
        header.setContentType(MediaType.APPLICATION_JSON);
        customHeaders.entrySet().forEach(e -> header.set(e.getKey(), e.getValue()));
      })
      .build();
  }
  public String getHost() {
    return url.getHost() + ":" + url.getPort();
  }

  public Mono<String> callProcedure(String id, String method, Object params) {
    logger.info("[JSON_RPC] Call method : {} - Start", method);
    logger.info("[JSON_RPC] params : {}", params);
    return Mono.fromCallable(() -> makeRequest(id, method, params))
      .publishOn(Schedulers.boundedElastic())
      .map(it -> {
        try {
          String result = send(it);
          return Optional.ofNullable(result)
              .orElseThrow(() -> new RpcCommException(ErrorCode.ConnectUrlErr(getHost() + " response is null. maybe connect error")));
        } catch (IOException e) {
          throw new RpcCommException(e.getMessage());
        } finally {
          logger.info("[JSON_RPC] Call method : {} - End", method);
        }
      })
      .onErrorResume(Mono::error);
  }

  private Map makeRequest(String id, String method, Object params) {
    Map<String, Object> request = new HashMap();
    request.put(RPC_TYPE_KEY, JSON_RPC_VERSION);
    request.put(METHOD_KEY, method);
    request.put(PARAMS_KEY, params);
    request.put(ID_KEY, id);
    return request;
  }
  private String send(Map request) throws IOException {
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      HttpURLConnection finalConnection = connection;
      headers.entrySet().forEach(e -> finalConnection.setRequestProperty(e.getKey(), e.getValue()));
      connection.setDoOutput(true);
      try (OutputStreamWriter w = new OutputStreamWriter(connection.getOutputStream())) {
        w.write(JSON.toJSONString(request));
      }
      try (InputStreamReader r = new InputStreamReader(connection.getInputStream())) {
        StringBuffer temp = new StringBuffer();
        int c = 0;
        while ((c = r.read()) != -1) {
          temp.append((char) c);
        }
        return temp.toString();
      }
    } catch (IOException e) {
      throw e;
    } finally {
      Optional.ofNullable(connection).ifPresent(it -> it.disconnect());
    }
  }

}
