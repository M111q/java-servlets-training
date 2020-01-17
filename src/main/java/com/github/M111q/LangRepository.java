package com.github.M111q;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LangRepository {

    Optional<Lang> findById(Integer langId) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Lang.class, langId);
        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }
}
