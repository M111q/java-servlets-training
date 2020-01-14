package com.github.M111q;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {

    private List<Lang> languages;

    LangRepository() {
        languages = new ArrayList<>();
        languages.add(new Lang(1L, "Witaj", "pl"));
        languages.add(new Lang(2L, "Hello", "en"));
        languages.add(new Lang(3L, "Willkommen", "de"));
    }

    Optional<Lang> findById(Long langId) {
        return languages
                .stream()
                .filter(lang -> lang.getId().equals(langId))
                .findFirst();
    }
}
