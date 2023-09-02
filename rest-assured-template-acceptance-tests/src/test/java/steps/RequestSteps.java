package steps;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import utils.WiremockUtil;

public class RequestSteps extends WiremockUtil {

    @Given("I have a valid value id to request data")
    public void validSetUp() {
        wireMockServer.stubFor(
                WireMock.get(WireMock.urlEqualTo("/rest-assured-template/data/single/1"))
                .willReturn(WireMock.aResponse().withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("needs sorting")));
    }

    @When("I submit a request using the value id <(\s)> but no headers")
    public void getRequest() {
        
    }
}
