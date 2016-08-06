package com.tracker.servlets;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseServlet extends HttpServlet {

    private static ComboPooledDataSource connectionPool = null;
    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String DB_URL = "jdbc:hsqldb:expense_tracker_db";
    private static final String USER = "sa";
    private static final String PASS = "password";

    public void init(ServletConfig config) throws ServletException {
        try
        {
            final ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(JDBC_DRIVER);
            cpds.setJdbcUrl(DB_URL);
            cpds.setUser(USER);
            cpds.setPassword(PASS);
            connectionPool = cpds;
            System.out.println("NOTE: DATABASE CONNECTION POOL STARTED");
            //dropExpenseTable();
            expenseInitialLoad();
            //testInitialLoad();
        }
        catch (PropertyVetoException pve)
        {
            pve.printStackTrace();
        }
    }


    public static Connection getConnection()
    {
        try
        {
            return connectionPool.getConnection();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void testInitialLoad() {
        try {
            Connection connection = DatabaseServlet.getConnection();
            if(connection != null) {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM test";
                ResultSet result = statement.executeQuery(query);
                result.next();
            } else {
                System.out.println("ERROR: connection is NULL");
            }
        }
        catch(SQLException sqle){
            try {
                System.out.println("NOTE: DATABASE DOES NOT EXIST, CREATING DATABASE");
                update("CREATE TABLE test (id INTEGER IDENTITY PRIMARY KEY, str VARCHAR(30))");
                update("INSERT INTO test (str) VALUES('Test1')");
                update("INSERT INTO test (str) VALUES('Test2')");
                update("INSERT INTO test (str) VALUES('Test3')");
                System.out.println("NOTE: DATABASE FINISHED CREATING");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void update(String sqlExpression) throws SQLException {
        Connection connection = DatabaseServlet.getConnection();
        if(connection != null) {
            System.out.println("========= sqlExpression: "+sqlExpression);
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate(sqlExpression);
            if (i == -1) {
                System.out.println("ERROR: database error in update "+sqlExpression);
            }
        }  else {
            System.out.println("ERROR: connection is NULL");
        }
    }

    private void expenseInitialLoad() {
        try {
            Connection connection = DatabaseServlet.getConnection();
            if(connection != null) {
                Statement statement = connection.createStatement();
                String query = "SELECT * FROM expTable";
                ResultSet result = statement.executeQuery(query);
                result.next();
            } else {
                System.out.println("ERROR: connection is NULL");
            }
        }
        catch(SQLException sqle){
            try {
                System.out.println("CREATING DATABASE, CREATING TABLE");
                update("CREATE TABLE expTable (id INTEGER IDENTITY PRIMARY KEY, expName VARCHAR(20), expAmount INTEGER, expDate DATE, expCategory VARCHAR(40))");
                update("INSERT INTO expTable (expName,expAmount,expDate,expCategory) VALUES('Test1',100,CURRENT_DATE,'MEAL')");
                update("INSERT INTO expTable (expName,expAmount,expDate,expCategory) VALUES('Test2',500,CURRENT_DATE,'TOOLS')");
                update("INSERT INTO expTable (expName,expAmount,expDate,expCategory) VALUES('Test3',1000,CURRENT_DATE,'UTILITIES')");
                System.out.println("TABLE CREATED");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void dropExpenseTable() {
        try {
            update("DROP TABLE expTable");
            System.out.println("TABLE DROPPED");
        }
        catch(SQLException sqle){
            System.out.println("DROP TABLE FAIL");
        }
    }


    public void destroy()
    {
        try
        {
            DataSources.destroy(connectionPool);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {}

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {}
}