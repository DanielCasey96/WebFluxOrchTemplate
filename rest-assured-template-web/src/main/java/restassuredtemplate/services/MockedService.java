package restassuredtemplate.services;

import reactor.core.publisher.Mono;
import restassuredtemplate.models.SingleDataResponse;

public class MockedService {
    public Mono<SingleDataResponse> getSingleData(String correlationId, String idValue) {
        return null;
    }
}
