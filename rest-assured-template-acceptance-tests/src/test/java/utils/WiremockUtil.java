package utils;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WiremockUtil {

    public static final WireMockServer wireMockServer = new WireMockServer(wireMockConfig().port(9090));

    @BeforeAll
    protected static void wiremockStart() {
        wireMockServer.start();
    }

    @AfterAll
    protected static void wiremockStop() {
        wireMockServer.stop();
    }
}
