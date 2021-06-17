package com.reactivespring.reactiveprogramming.FluxandMonoPlayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static reactor.core.scheduler.Schedulers.parallel;

public class filtersinFlux {

    @Test
    public void filterWithname(){
        List<String> name= Arrays.asList("sheik","abu","abdull");

       Flux<String> k=Flux.fromIterable(name)
                .filter(i->i.equals("abu")).log();
        StepVerifier.create(k)
                .expectNext("abu").verifyComplete();
    }
    @Test
    public void filterlength(){
        List<String> name= Arrays.asList("sheik","abu","abdull");
        Flux<String> k=Flux.fromIterable(name)
                .filter(e->e.length()>3)
                .log();
        k.subscribe(
                System.out::println,null,()->System.out.println("successss")
        );
    }

    @Test
    public void filtersandMap(){
        List<String> name= Arrays.asList("sheik","abu","abdull","hi","hussain");

        Flux<String> k=Flux.fromIterable(name)
                .filter(s->s.length()<5)
                .concatWithValues("-----------------")
                .repeat(2)
                .map(s->s.toUpperCase());

        k.subscribe(
                System.out::println,null,()->System.out.println("testing done....")
        );
    }
    @Test
    public void flapmapexample(){
        List<String> name=Arrays.asList("a","b","c","d","e","f","g");

        Flux<String> t=Flux.fromIterable(name)
                .window(2) //flux[A,B]
                .flatMap((s)->
                    s.map(this::getfluxobject).subscribeOn(parallel()))
                .flatMap(s->Flux.fromIterable(s))
                .log();

        t.subscribe(System.out::println);
    }

    public List<String> getfluxobject(String s){
        return Arrays.asList(s);
    }
}
