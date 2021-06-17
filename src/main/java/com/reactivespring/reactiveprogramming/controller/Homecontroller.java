package com.reactivespring.reactiveprogramming.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.time.Duration;

@RestController
public class Homecontroller {

    @GetMapping(value = "/getnum",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Integer> getnum(){
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
    @GetMapping(value = "/getnum1")
    public Flux<Integer> getnum1(){
        return Flux.range(1,10)
                //.delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/getnummono")
    public Mono<Integer> getnummono(){
        return Mono.just(1)
                //.delayElements(Duration.ofSeconds(1))
                .log();
    }
}
