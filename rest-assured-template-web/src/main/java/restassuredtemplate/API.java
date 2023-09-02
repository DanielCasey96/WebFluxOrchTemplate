package restassuredtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class, ErrorWebFluxAutoConfiguration.class})
public class API {

    public static void main(String[] args) {
        SpringApplication.run(API.class, args);
    }
}
