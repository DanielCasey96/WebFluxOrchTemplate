package restassuredtemplate.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import restassuredtemplate.services.MockedProvider;

@Configuration
public class ProviderConfig {

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
            @Value("http://localhost:9090") String mockedServiceUrl,
            @Value("1000") Integer connectTimeout,
            @Value("1000") Integer readTimeout,
            @Value("1000") Integer writeTimeout) {

        return new MockedProvider(
                createWebClient(mockedServiceUrl, connectTimeout, readTimeout, writeTimeout));
    }
}
