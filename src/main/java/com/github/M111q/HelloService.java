package com.github.M111q;

import java.util.Optional;

class HelloService {

    private static final String DEFAULT_NAME = "world";

    String prepareGreeting(String name) {
        return "Hello " + Optional.ofNullable(name).orElse(DEFAULT_NAME) + "!";
    }
}
