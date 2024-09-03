package dev.thesarfo.webfluxdemo;

import dev.thesarfo.webfluxdemo.dto.MultiplyRequestDto;
import dev.thesarfo.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Lec03PostRequestTest extends BaseTest{

    @Autowired
    private WebClient webClient;

    @Test
    void postTest(){
        Mono<Response> responseMono = this.webClient
                .post()
                .uri("reactive-math/multiply")
                .bodyValue(buildRequestDto(5, 2))
                .retrieve()
                .bodyToMono(Response.class)
                .doOnNext(System.out::println);


        StepVerifier.create(responseMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    private MultiplyRequestDto buildRequestDto(int a, int b){
        MultiplyRequestDto dto = new MultiplyRequestDto();
        dto.setFirst(a);
        dto.setSecond(b);

        return dto;
    }
}
