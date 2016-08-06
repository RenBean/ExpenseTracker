package com.tracker.data.util;

import com.tracker.data.Expense;
import com.tracker.servlets.DatabaseServlet;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Ari on 8/6/16.
 */
public class ExpenseData {
    public static ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        try
        {
            Connection connection = DatabaseServlet.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT id FROM expTable";
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                Expense expense = new Expense(result.getInt("id"));
                expenses.add(expense);
            }
            result.close();
            statement.close();
            connection.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return expenses;
    }

    public static ArrayList<Expense> getExpCategory(Expense.ExpCategory expCategory) {
        ArrayList<Expense> expenses = new ArrayList<>();
        try
        {
            Connection connection = DatabaseServlet.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT id FROM expTable WHERE expCategory = ?");
            statement.setString(1,expCategory.toString());
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Expense expense = new Expense(result.getInt("id"));
                expenses.add(expense);
            }
            result.close();
            statement.close();
            connection.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return expenses;
    }

}
