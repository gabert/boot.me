package com.github.gabert.bootme.controller.versiondemo;

public class HelloV2Response {
    private final String fullName;

    public HelloV2Response(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
