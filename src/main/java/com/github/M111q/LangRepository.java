package com.github.M111q;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {

    private List<Lang> languages;

    LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1, "Witaj", "pl"));
        languages.add(new Lang(2, "Hello", "en"));
        languages.add(new Lang(3, "Willkommen", "de"));
    }

    Optional<Lang> findById(Integer langId) {
        return languages
                .stream()
                .filter(lang -> lang.getId().equals(langId))
                .findFirst();
    }
}
