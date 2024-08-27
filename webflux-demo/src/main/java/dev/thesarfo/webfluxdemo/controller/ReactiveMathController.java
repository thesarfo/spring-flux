package dev.thesarfo.webfluxdemo.controller;

import dev.thesarfo.webfluxdemo.dto.Response;
import dev.thesarfo.webfluxdemo.service.ReactiveMathService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

    private final ReactiveMathService mathService;


    public ReactiveMathController(ReactiveMathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("square/{input}")
    public Mono<Response> findSquare(@PathVariable int input) {
        return mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public Flux<Response> multiplicationTable(@PathVariable int input){
        return mathService.multiplicationTable(input);
    }

    /**
     *
     * @param input
     * @return a stream of data which is sent to the subscriber as and when it is produced
     */
    @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> multiplicationTableStream(@PathVariable int input){
        return mathService.multiplicationTable(input);
    }
}
