package com.github.M111q.hello;

import com.github.M111q.hello.HelloService;
import com.github.M111q.lang.Lang;
import com.github.M111q.lang.LangRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HelloServiceTest {

    public static final String WELCOME_MSG = "Hello";
    public static final String FALLBACK_ID_WELCOME = "Hola";

    @Test
    public void test_prepareGreeting_ShouldReturnDefaultGreeting() {
        // given
        var mockRepository = alwaysReturningHelloLangRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, -1);
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
        var result = SUT.prepareGreeting(name, -1);
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
/*

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
*/

    // test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang

    @Test
    public void test_prepareGreeting_ShouldReturnDefaultLangGreeting_emptyLang() {
        // given
        String wrongLangId = "test";
        var mockRepository = alwaysReturnEmptyRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, -1);
        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository alwaysReturnEmptyRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer langId) {
                return Optional.empty();
            }
        };
    }


    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            public Optional<Lang> findById(Integer langId) {
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
            public Optional<Lang> findById(Integer langId) {
                return Optional.of(new Lang(null, WELCOME_MSG, null));
            }
        };
    }
}