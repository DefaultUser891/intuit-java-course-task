package com.mypongserver.servlet;

import com.mypongserver.page.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloWordServlet extends HttpServlet {
    protected AtomicInteger idGenerator;
    protected Boolean useHttpSession;

    @Override
    public void init() throws ServletException {
        useHttpSession = Boolean.valueOf(getServletConfig().getInitParameter("useHttpSession"));
        idGenerator = new AtomicInteger(0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendHelloWordResponse(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sendHelloWordResponse(req, resp);
    }

    protected void sendHelloWordResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean newUser = false;
        String id = "";

        if (useHttpSession) {
            HttpSession session = req.getSession();
            if (session.getAttribute("id") == null) {
                newUser = true;
                session.setAttribute("id", idGenerator.getAndIncrement());
            }
            id = String.valueOf(session.getAttribute("id"));
        } else {
            id = req.getParameter("id");
            if (id == null || id.isEmpty()) {
                newUser = true;
                id = String.valueOf(idGenerator.getAndIncrement());
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        PageGenerator.writePage(id, resp.getWriter(), newUser);
    }
}
