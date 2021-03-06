package com.github.M111q.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "Hello", urlPatterns = {"/api"})
public class HelloServlet extends HttpServlet {
    private static final String REQ_PARAM_NAME = "name";
    private static final String REQ_PARAM_LANG = "lang";

    private final Logger logger = LoggerFactory.getLogger(HelloServlet.class);

    private HelloService service;

    /**
     * For servlet container
     */
    @SuppressWarnings("unused")
    public HelloServlet() {
        this(new HelloService());
    }

    HelloServlet(HelloService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request got, parameters: " + req.getParameterMap());

        var parameterName = req.getParameter(REQ_PARAM_NAME);
        var parameterLang = req.getParameter(REQ_PARAM_LANG);

        Integer langId = null;
        try {
            langId = Integer.valueOf(parameterLang);
        } catch (NumberFormatException e) {
            logger.warn("wrong lang Id format (non numeric) " + parameterLang);
        }

        resp.getWriter().write(service.prepareGreeting(parameterName, langId));
    }
}
