package com.github.M111q.todo;

import com.github.M111q.HibernateUtil;
import com.github.M111q.lang.Lang;

import java.util.List;

class TodoRepository {

    List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();

        return result;
    }




}
