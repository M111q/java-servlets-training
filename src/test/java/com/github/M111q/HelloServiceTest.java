package com.github.M111q;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HelloServiceTest {

    public static final String WELCOME_MSG = "Hello";
    private HelloService SUT;

/*
    @Before
    public void setUp() {
        SUT = new HelloService();

    }
*/

    @Test
    public void test_prepareGreeting_ShouldReturnDefaultGreeting() {
        // given
        var mockRepository = alwaysReturningHelloLangRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "-1");
        // then
        assertEquals(WELCOME_MSG + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_ShouldReturnGreetingWithName() {
        // given
        var mockRepository = alwaysReturningHelloLangRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        // when
        var result = SUT.prepareGreeting(name, "-1");
        // then
        assertEquals(WELCOME_MSG + " " + name + "!", result);
    }

    @Test
    public void test_prepareGreeting_ShouldReturnDefaultLangGreeting() {
        // given
        var fallbackIdWelcome = "Hola";
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Long langId) {
                if (langId.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of((new Lang(null, fallbackIdWelcome, null)));
                }
                return Optional.empty();
            }
        };

        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);
        // then
        assertEquals(fallbackIdWelcome + " " + HelloService.FALLBACK_NAME + "!", result);
    }

//    @Test
//    public void test_prepareGreeting_ShouldReturnGreetingWithGivenName() {
//
//        var name = "test";
//
//        var result = SUT.prepareGreeting(name , null);
//
//        assertEquals("Hello " + name + "!", result);
//    }
//
//    @Test
//    public void test_prepareGreeting_ShouldReturnGreetingWithBlank() {
//
//        var name = "";
//
//        var result = SUT.prepareGreeting(name, null);
//
//        assertEquals("Hello " + name + "!", result);
//    }

    private LangRepository alwaysReturningHelloLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long langId) {
                return Optional.of(new Lang(null, WELCOME_MSG, null));
            }
        };
    }
}