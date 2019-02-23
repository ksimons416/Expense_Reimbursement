package com.spark.ex.web;

import com.spark.ex.util.ConnectionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Initialize Application context");
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter("dbUrl");
        String username = context.getInitParameter("dbUsername");
        String password = context.getInitParameter("dbPassword");
        String dbDriverName = context.getInitParameter("dbDriver");
        Driver driver;
        try {
            driver = (Driver) Class.forName(dbDriverName).newInstance();
            ConnectionUtil.createConnectionManager(url, username, password, driver);
        } catch (InstantiationException e) {
            throw new IllegalStateException("Could not init db driver");
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not init db driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Could not init db driver");
        } catch (SQLException e) {
            throw new IllegalStateException("Could not init connection manager");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
