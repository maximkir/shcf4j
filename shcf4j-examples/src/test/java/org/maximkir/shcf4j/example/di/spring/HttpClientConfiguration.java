package org.maximkir.shcf4j.api.example.di.spring;

import org.maximkir.shcf4j.api.HttpClientBuilderFactory;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

    @Bean
    public SyncHttpClient asyncHttpClient(){
        return HttpClientBuilderFactory.getHttpClientBuilder().build();
    }

}
