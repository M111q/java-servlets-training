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

    Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        Todo result = session.createQuery("from Todo T where T.id = " + id, Todo.class).getSingleResult();

        var todo = session.get(Todo.class, id);

        result.setDone(!result.isDone());

        transaction.commit();
        session.close();

        return result;

    }

    public static void main(String[] args) {
        /*TodoRepository repository = new TodoRepository();
        repository.findAll().forEach(e -> System.out.println(e));
        System.out.println("########################");
        System.out.println(repository.toggleTodo(1));
        System.out.println("########################");

        System.out.println(repository.toggleTodo(1));*/


    }


}
