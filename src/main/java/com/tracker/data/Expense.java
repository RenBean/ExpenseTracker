package com.tracker.data;

import com.tracker.servlets.DatabaseServlet;

import java.sql.*;

/**
 * Created by Ari on 8/6/16.
 */
public class Expense {
    public static enum ExpCategory { MEAL, HOTEL, TRANSPORTATION, TOOLS, UTILITIES, SUPPLIES };

    private int id;
    private String expName;
    private int expAmount;
    private String expDate;
    private ExpCategory expCategory;
    private boolean exists = false;

    public Expense () {

    }
    public Expense (int id) {load (id);}

    public void load(int id) {
        try
        {
            Connection connection = DatabaseServlet.getConnection();

            Statement statement = connection.createStatement();
            String query = "SELECT expName, expAmount, expDate, expCategory FROM expTable WHERE id = "+id;
            ResultSet result = statement.executeQuery(query);
            result.next();
            this.id = id;
            this.setExpName(result.getString("expName"));
            this.setExpAmount(result.getInt("expAmount"));
            this.setExpDate(result.getString("expDate"));
            this.setExpCategory(ExpCategory.valueOf(result.getString("expCategory")));
            exists = true;

            result.close();
            statement.close();
            connection.close();
        }
        catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    public void saveNew() {
        if(!exists){
            try
            {
                Connection connection = DatabaseServlet.getConnection();

                String query = "INSERT INTO expTable (expName, expAmount, expDate, expCategory) VALUES (?,?,?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1,this.getExpName());
                preparedStatement.setInt(2,this.getExpAmount());
                preparedStatement.setString(3,this.getExpDate());
                preparedStatement.setString(4,this.getExpCategory().toString());
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Object already exists in database. Don't use saveNew(), use update().");
        }
    }

    private void update() {
        if(exists){
            try
            {
                Connection connection = DatabaseServlet.getConnection();

                String query = "UPDATE expTable SET expName = ?, expAmount = ?, expDate = ?, expCategory = ? WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,this.getExpName());
                preparedStatement.setInt(2,this.getExpAmount());
                preparedStatement.setString(3,this.getExpDate());
                preparedStatement.setString(4,this.getExpCategory().toString());
                preparedStatement.setInt(5,this.getId());
                preparedStatement.executeUpdate();

                preparedStatement.close();
                connection.close();
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Object does not exist in database yet. Don't use update(), use saveNew().");
        }
    }

    private void delete(){
        if(exists){
            try
            {
                Connection connection = DatabaseServlet.getConnection();

                String query = "DELETE FROM expTable WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,this.getId());
                preparedStatement.executeUpdate();
                exists = false;

                preparedStatement.close();
                connection.close();
            }
            catch(SQLException sqle){
                sqle.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Object does not exist in database yet. You must load() object before you can delete()");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public int getExpAmount() {
        return expAmount;
    }

    public void setExpAmount(int expAmount) {
        this.expAmount = expAmount;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public ExpCategory getExpCategory() {
        return expCategory;
    }

    public void setExpCategory(ExpCategory expCategory) {
        this.expCategory = expCategory;
    }
}


