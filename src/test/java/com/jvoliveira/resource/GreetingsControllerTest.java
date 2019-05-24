package com.jvoliveira.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
public class GreetingsControllerTest {

    @Inject
    EmbeddedServer embeddedServer;
    
    @Inject
    @Client("/greetings")
    GreetingsControllerRoutes client;
    
    @Value("${greeting.message}")
    String greetingMessage;

    @Test
    public void testIndex() throws Exception {
        try(RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/greetings").status());
        }
    }
    
    @Test
    public void greetingsByNameTest() {
    	HttpResponse<String> response = client.greeting("joao");
    	String responseText = response.body();
    	assertEquals(responseText, greetingMessage + ", joao!" );
    }
}
