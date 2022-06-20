package restassuredtemplate.services;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import restassuredtemplate.models.MultipleDataResponses;
import restassuredtemplate.models.SingleDataResponse;

import static restassuredtemplate.common.Constants.CORRELATION_ID_HEADER;

public class MockedProvider {

    private final WebClient apiClient;
    public MockedProvider(WebClient apiClient) {
        this.apiClient = apiClient;
    }

    public Mono<SingleDataResponse> getSingleData(String correlationId, Integer idValue) {
        return apiClient
                .get()
                .uri("/rest-assured-template/service/data/single/{idValue}", idValue)
                .header(CORRELATION_ID_HEADER, correlationId)
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .bodyToMono(SingleDataResponse.class);
    }

    public Flux<MultipleDataResponses> getMultipleData(String correlationId, Integer idValue) {
        return null;
    }

    public WebClient createWebClient(String url, Integer connectTimeout, Integer readTimeout, Integer writeTimeout) {

        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout)
                .doOnConnected(connection -> connection
                    .addHandlerLast(new ReadTimeoutHandler(readTimeout))
                    .addHandlerLast(new WriteTimeoutHandler(writeTimeout)));

        return WebClient.builder()
                .baseUrl(String.format("%s", url))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }

    @Bean
    public MockedProvider mockedProvider(
            @Value("http://localhost:4444") String mockedServiceUrl,
            @Value("1000") Integer connectTimeout,
            @Value("1000") Integer readTimeout,
            @Value("1000") Integer writeTimeout) {

        return new MockedProvider(
                createWebClient(mockedServiceUrl, connectTimeout, readTimeout, writeTimeout));
    }

}
