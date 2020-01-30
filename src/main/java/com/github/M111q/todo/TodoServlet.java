package com.github.M111q.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Todo", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);
    private ObjectMapper mapper;
    private TodoRepository repository;

    public TodoServlet() {
        this(new ObjectMapper(), new TodoRepository());
    }

    public TodoServlet(ObjectMapper mapper, TodoRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got, parameters: " + req.getParameterMap());

        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Integer todoId = null;
        try {
            pathInfo = pathInfo.substring(1);
            todoId = Integer.valueOf(pathInfo);
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(), repository.toggleTodo(todoId));
        } catch (NumberFormatException e) {
            logger.warn("wrong todo Id format (non numeric) " + pathInfo);
        } catch (StringIndexOutOfBoundsException e) {
            logger.warn("wrong todo Id format (empty string) " + pathInfo);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Todo newTodo = mapper.readValue(req.getInputStream(),Todo.class);
        newTodo = repository.addTodo(newTodo);

        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), newTodo);
    }
}
