package com.github.M111q;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HelloServiceTest {

    public static final String WELCOME_MSG = "Hello";
    public static final String FALLBACK_ID_WELCOME = "Hola";

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
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, null);
        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void test_prepareGreeting_ShouldReturnDefaultLangGreeting_textLang() {
        // given
        String wrongLangId = "test";
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, wrongLangId);
        // then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long langId) {
                if (langId.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of((new Lang(null, FALLBACK_ID_WELCOME, null)));
                }
                return Optional.empty();
            }
        };
    }


    private LangRepository alwaysReturningHelloLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long langId) {
                return Optional.of(new Lang(null, WELCOME_MSG, null));
            }
        };
    }
}