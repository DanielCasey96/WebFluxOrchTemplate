package restassuredtemplate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import restassuredtemplate.models.MultipleDataResponses;
import restassuredtemplate.models.SingleDataResponse;

import static restassuredtemplate.common.Constants.CONTENT_TYPE;
import static restassuredtemplate.common.Constants.CORRELATION_ID_HEADER;

public class MockedProvider {

    private final WebClient apiClient;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MockedProvider(WebClient apiClient) {

        this.apiClient = apiClient;
    }

    public Mono<SingleDataResponse> getSingleData(String correlationId, Integer idValue, String contentType) {
        return apiClient
                .get()
                .uri("/rest-assured-template/service/data/single/{idValue}", idValue)
                .header(CORRELATION_ID_HEADER, correlationId)
                .header(CONTENT_TYPE, contentType)
                .accept(MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE))
                .retrieve()
                .bodyToMono(SingleDataResponse.class)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception("EMPTY BODY RECEIVED"))))
                .doOnNext(response -> logger.info(String.format("Service Response Body: " + response.getSingleData())));
    }

    public Flux<MultipleDataResponses> getMultipleData(String correlationId, Integer idValue, String contentType) {
        return null;
    }

}
