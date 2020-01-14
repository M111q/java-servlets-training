package com.github.M111q;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Hello", urlPatterns = {"/api/*"})
public class HelloServlet extends HttpServlet {
    private static final String DEFAULT_NAME = "world";
    private static final String NAME = "name";
    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional param = Optional.ofNullable(req.getParameter(NAME));
        var name = param.orElse(DEFAULT_NAME) + "!";

        logger.info("Request got, parameters: " + req.getParameterMap());
        resp.getWriter().write("Hello " + name);
    }
}
