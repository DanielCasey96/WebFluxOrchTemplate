package restassuredtemplate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import restassuredtemplate.models.SingleDataResponse;

@Service
public class MockedService {

    private final MockedProvider mockedProvider;

    @Autowired
    public MockedService(MockedProvider mockedProvider) {
        this.mockedProvider = mockedProvider;
    }

    public Mono<SingleDataResponse> getSingleDataResponse(String correlationId, Integer idValue) {
        return mockedProvider.getSingleData(correlationId, idValue);
    }
}
