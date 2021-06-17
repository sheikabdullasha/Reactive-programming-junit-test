package com.reactivespring.reactiveprogramming.FluxandMonoPlayground;

import org.junit.Test;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class BackPressureTest {
    @Test
    public void backpressure(){
        Flux<Integer> k=Flux.just(1,2,3,4,5).log();

        StepVerifier.create(k)
                .thenRequest(1)
                .expectNext(1)
                .thenRequest(1)
                .expectNext(2)
                .thenCancel()
                .verify();

    }

    @Test
    public void backpressureTest(){
        Flux<Integer> k=Flux.just(1,2,3,4,5).log();

        k.subscribe(
                (e)->System.out.println("element is :"+e),
                null,null,(s->s.request(2))
        );

    }

    @Test
    public void backpressureTest_cancel(){
        Flux<Integer> k=Flux.just(1,2,3,4,5).log();

        k.subscribe(
                (e)->System.out.println("element is :"+e),
                null,null,(s->s.cancel())
        );

    }

    @Test
    public void backpressureTest_customise(){
        Flux<Integer> k=Flux.just(1,2,3,4,5).log();

        k.subscribe(
                new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnNext(Integer value) {
                        request(1);
                        if(value==4){
                            cancel();
                        }
                    }
                }
        );

    }
}
