package dev.thesarfo.webfluxdemo;

import dev.thesarfo.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


/**
 * This class demonstrates how you can make a get request with Webclient expecting a response Publisher of mono
 */
public class Lec01GetSingleResponseTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    public void blockTest(){
        Response response = this.webClient
                .get() // http request method
                .uri("reactive-math/square/{input}", 5) // exact url and path variables
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        System.out.println(response);
    }


    @Test
    public void stepVerifierTest(){
        Mono<Response> responseMono = this.webClient
                .get() // http request method
                .uri("reactive-math/square/{input}", 5) // exact url and path variables
                .retrieve()
                .bodyToMono(Response.class);


        StepVerifier.create(responseMono)
                .expectNextMatches(r -> r.getOutput() == 25)
                .verifyComplete();
    }
}
