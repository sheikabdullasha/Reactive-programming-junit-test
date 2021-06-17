package com.reactivespring.reactiveprogramming.FluxandMonoPlayground;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

public class Fluxandmonofactorytest {

    @Test
    public void listtoflux(){
        List<String> name= Arrays.asList("sheik","abu","abdull");

        Flux<String> t=Flux.fromIterable(name).log();

        StepVerifier.create(t)
                .expectNext("sheik","abu","abdull")
                .verifyComplete();

    }

    @Test
    public void arraytoflux(){
        String[] str=new String[]{"sheik","abu"};
        Flux<String> flu=Flux.fromArray(str);

        StepVerifier.create(flu)
                .expectNext("sheik","abu")
                .verifyComplete();

    }
    @Test
    public void fluxfromrange(){
        Flux<Integer> fluxNum=Flux.range(1,10).log();

        /*StepVerifier.create(fluxNum)
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();*/

        fluxNum.subscribe(
                System.out::println
        );
    }
    @Test
    public void monousingJustorEmpty(){
        Mono l=Mono.justOrEmpty(null);
        StepVerifier.create(l)
                .verifyComplete();


    }


}
