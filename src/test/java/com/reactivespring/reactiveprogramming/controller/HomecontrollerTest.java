package com.reactivespring.reactiveprogramming.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebFluxTest
public class HomecontrollerTest {

    @Autowired
    WebTestClient WebTestClient;

    @Test
    public void flux_approach_test() {
        Flux<Integer> t = WebTestClient.get().uri("/getnum1")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class)
                .getResponseBody();

        StepVerifier.create(t)
                .expectSubscription()
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10
                ).verifyComplete();
    }
    /**************************checking testcase size**************************/
    @Test
    public void flux_approach_test1() {
        WebTestClient.get().uri("/getnum1")

                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(10);


    }

    @Test
    public void flux_approach_test3() {
        WebTestClient.get().uri("/getnum1")

                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(Integer.class)
                .hasSize(10);


    }

    @Test
    public void testMono(){
        Integer i=new Integer(1);
        WebTestClient.get().uri("/getnummono")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Integer.class)
                .consumeWith((response)->{
                   assertEquals(i,response.getResponseBody());
                });

    }
}
