package com.example.demo.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Example controller demonstrating
 *
 * a) API versioning by media type negotiation {@link #sayHelloV1(String)} {@link #sayHelloV2(String)}
 * b) API response for not supported version {@link #sayHelloV3(String)}
 * c) Two different ways to build response
 *    1) ResponseEntity return {@link #sayHelloV1(String)}
 *    2) POJO return {@link #sayHelloV2(String)}
 * d) valid vendor media type 'application/vnd.example.hello'
 */
@RestController
public class HelloController {
    private static final String MEDIA_TYPE_EXAMPLE_HELLO = "application/vnd.example.hello";

    private static final String HELLO_TEMPLATE_V1 = "Hello %s V1 !";
    private static final String HELLO_TEMPLATE_V2 = "Hello %s V2 !";

    @Operation(summary = "Say hello to ... ",  description = "Endpoint says hello to name specified.")
    @GetMapping(value = "/hello/{name}", produces = MEDIA_TYPE_EXAMPLE_HELLO + "-v1+json")
    public ResponseEntity<HelloV1> sayHelloV1(@PathVariable("name") String name) {
        return ResponseEntity.ok(new HelloV1(String.format(HELLO_TEMPLATE_V1, name)));
    }

    // Method introduced breaking change in data returned
    @GetMapping(value = "/hello/{name}", produces = MEDIA_TYPE_EXAMPLE_HELLO + "-v2+json")
    public HelloV2 sayHelloV2(@PathVariable("name") String name) {
        return new HelloV2(String.format(HELLO_TEMPLATE_V2, name));
    }

    @Deprecated
    @GetMapping(value = "/hello/{name}", produces = MEDIA_TYPE_EXAMPLE_HELLO + "-v3+json")
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public DeprecatedMethod sayHelloV3(@PathVariable("name") String name) {
        return new DeprecatedMethod(MEDIA_TYPE_EXAMPLE_HELLO + "-v2+json");
    }
}
