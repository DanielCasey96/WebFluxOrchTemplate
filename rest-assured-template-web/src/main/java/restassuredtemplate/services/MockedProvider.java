package restassuredtemplate.services;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

}
