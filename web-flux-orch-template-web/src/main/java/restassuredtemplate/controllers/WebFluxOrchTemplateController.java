package restassuredtemplate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import restassuredtemplate.models.MultipleDataResponses;
import restassuredtemplate.models.SingleDataResponse;
import restassuredtemplate.services.MockedProvider;

@RestController
@Validated
public class WebFluxOrchTemplateController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private final MockedProvider mockedProvider;

    @Autowired
    public WebFluxOrchTemplateController(MockedProvider mockedProvider) {

        this.mockedProvider = mockedProvider;
    }

    @GetMapping(value = "/web-flux-orch-template/data/single/{idValue}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<SingleDataResponse>> getSingleData(
            @RequestHeader("Correlation-Id") String correlationId,
            @PathVariable Integer idValue) {

        String trackingTag = String.format("correlation id: %s, value id: %s", correlationId, idValue.toString());
        logger.info(String.format("Getting data (%s)", trackingTag));

        return ResponseEntity.ok()
                .body(mockedProvider.getSingleData(correlationId, idValue)
                .doOnNext(response -> logger
                    .info(String.format("Data retrieved Successfully (%s)", trackingTag)))
                .doOnError(error -> logger
                    .info(String.format("Getting data failed (%s)", error, trackingTag))));
    }

    @GetMapping(value = "/web-flux-orch-template/data/multiple/{idValue}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Flux<MultipleDataResponses>> getMultipleData(
            @RequestHeader("Correlation-Id") String correlationId,
            @PathVariable Integer idValue) {

        String trackingTag = String.format("correlation id: %s, value id: %s", correlationId, idValue.toString());
        logger.info(String.format("Getting data (%s)", trackingTag));

        return ResponseEntity.ok()
                .body(mockedProvider.getMultipleData(correlationId, idValue)
                        .doOnNext(response -> logger
                                .info(String.format("Data retrieved Successfully (%s)", trackingTag)))
                        .doOnError(error -> logger
                                .info(String.format("Getting data failed (%s)", error, trackingTag))));
    }

}
