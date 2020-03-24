package com.github.gabert.bootme.controller.versiondemo;

public class HelloV1Response {
    private final String name;

    public HelloV1Response(String name) {
        this.name = name;
    }

    public String getContent() {
        return name;
    }
}
