package com.github.M111q;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloServiceTest {

    private HelloService SUT;

    @Before
    public void setUp() {
        SUT = new HelloService();
    }

    @Test
    public void prepareGreetingShouldReturnDefaultGreeting() {

        var result = SUT.prepareGreeting(null);

        assertEquals("Hello " + HelloService.DEFAULT_NAME + "!", result);
    }

    @Test
    public void prepareGreetingShouldReturnGreetingWithGivenName() {

        var name = "test";

        var result = SUT.prepareGreeting(name);

        assertEquals("Hello " + name + "!", result);
    }

    @Test
    public void prepareGreetingShouldReturnGreetingWithBlank() {

        var name = "";

        var result = SUT.prepareGreeting(name);

        assertEquals("Hello " + name + "!", result);
    }

}