package org.maximkir.shcf4j.example.di.spring;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.maximkir.shcf4j.api.HttpHost;
import org.maximkir.shcf4j.api.HttpRequestBuilder;
import org.maximkir.shcf4j.api.client.SyncHttpClient;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.net.HttpURLConnection;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BootstrapHttpClientThroughSpringBeans.class})
@Configuration
@ComponentScan(basePackages = {"org.maximkir.shcf4j.example.di.spring"})
public class BootstrapHttpClientThroughSpringBeans {

    private static final int WIREMOCK_PORT = 8089;

    @Rule
    public WireMockClassRule instanceRule = new WireMockClassRule(WIREMOCK_PORT);


    @Autowired
    private SyncHttpClient httpClient;

    @Test
    public void springBeanBootstrapTest() {

        instanceRule
                .stubFor(get(urlEqualTo("/"))
                        .willReturn(aResponse().withStatus(HttpURLConnection.HTTP_OK)));

        httpClient.execute(
                HttpHost.builder().schemeName("http").hostname("localhost").port(WIREMOCK_PORT).build(),
                HttpRequestBuilder.GET().uri("/").build(),
                response -> {
                    System.out.println(response.getStatusLine());
                    return response.getStatusLine().getStatusCode() == 200;
                });
    }
}
