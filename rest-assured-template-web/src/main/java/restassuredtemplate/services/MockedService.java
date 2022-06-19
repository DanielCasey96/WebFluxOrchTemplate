package restassuredtemplate.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import restassuredtemplate.models.MultipleDataResponses;
import restassuredtemplate.models.SingleDataResponse;

public class MockedService {
    public Mono<SingleDataResponse> getSingleData(String correlationId, String idValue) {
        return null;
    }
    public Flux<MultipleDataResponses> getMultipleData(String correlationId, String idValue) {
        return null;
    }
}
