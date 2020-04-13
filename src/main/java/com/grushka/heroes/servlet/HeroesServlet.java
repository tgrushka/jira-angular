package com.grushka.heroes.servlet;

import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.atlassian.templaterenderer.TemplateRenderer;

@Scanned
public class HeroesServlet extends HttpServlet{
    private static final Logger log = LoggerFactory.getLogger(HeroesServlet.class);

    @ComponentImport
    private final TemplateRenderer templateRenderer;

    public HeroesServlet(TemplateRenderer templateRenderer) {
        this.templateRenderer = templateRenderer;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/html");
        Map<String, Object> context = new HashMap<>();
        templateRenderer.render("index.html", context, resp.getWriter());
    }

}