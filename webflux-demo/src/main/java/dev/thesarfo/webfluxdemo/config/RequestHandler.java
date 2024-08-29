package dev.thesarfo.webfluxdemo.config;

import dev.thesarfo.webfluxdemo.dto.InputFailedValidationResponse;
import dev.thesarfo.webfluxdemo.dto.MultiplyRequestDto;
import dev.thesarfo.webfluxdemo.dto.Response;
import dev.thesarfo.webfluxdemo.exceptions.InputValidationException;
import dev.thesarfo.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This is the request handler for the functional endpoints
 */
@Service
public class RequestHandler {

    private final ReactiveMathService mathService;


    public RequestHandler(ReactiveMathService mathService) {
        this.mathService = mathService;
    }

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Mono<Response> responseMono = mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = mathService.multiplicationTable(input);
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    /**
     *
     * @param serverRequest
     * @return a stream of data to the consumer
     */
    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        Flux<Response> responseFlux = mathService.multiplicationTable(input);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest){
        Mono<MultiplyRequestDto> requestDtoMono = serverRequest.bodyToMono(MultiplyRequestDto.class);
        Mono<Response> responseMono = mathService.multiply(requestDtoMono);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest){
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if (input < 10 || input > 20){
            return Mono.error(new InputValidationException(input));
        }
        Mono<Response> responseMono = mathService.findSquare(input);
        return ServerResponse.ok().body(responseMono, Response.class);
    }
}
