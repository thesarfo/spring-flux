package dev.thesarfo.webfluxdemo;

import dev.thesarfo.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class Lec02GetMultiResponseTest extends BaseTest {

    @Autowired
    private WebClient webClient;


    @Test
    void fluxTest(){
        Flux<Response> responseFlux = this.webClient
                .get()
                .uri("reactive-math/table/{input}", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseFlux)
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    void fluxStreamTest(){
        Flux<Response> responseStream = this.webClient
                .get()
                .uri("reactive-math/table/{input}/stream", 5)
                .retrieve()
                .bodyToFlux(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseStream)
                .expectNextCount(10)
                .verifyComplete();
    }
}
