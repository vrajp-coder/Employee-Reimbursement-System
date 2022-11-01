package com.revature.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initializing servlet context...");
        System.out.println("Servlet context initialization successful");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroying servlet context");
    }
}