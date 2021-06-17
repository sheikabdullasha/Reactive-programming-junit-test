package com.reactivespring.reactiveprogramming.FluxandMonoPlayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxandmonoTest {
    @Test
    public void FluxTest(){

       Flux<String> fluxValue= Flux.just("sheik","abu","bismi")
               .concatWith(Flux.error(new RuntimeException("error in flux")))
        .log();

       fluxValue.subscribe(
             System.out::println,(k)->System.err.println("Error occured in program"),()->System.out.println("completed successfully")
       );

    }

    @Test
    public void fluxandmonowithout_error(){
        Flux<String> flu=Flux.just("sheik","hussain","remi");

        StepVerifier.create(flu)
                .expectNext("sheik","hussain","remi")
                .verifyComplete();


    }
    @Test
    public void monotesting(){
        Mono str=Mono.just("hello you").log();

        str.subscribe(
                System.out::println,(e)->System.err.println("error in code"),()->System.out.println("completed successfuly!!!!")
        );
    }

    @Test
    public void monotestingwitherror(){
        Mono str=Mono.error(new RuntimeException("error in mono"))
                .log();

        str.subscribe(
                System.out::println,(e)->System.err.println("error in code"),()->System.out.println("completed successfuly!!!!")
        );
    }
}
