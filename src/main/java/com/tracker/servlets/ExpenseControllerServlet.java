package com.tracker.servlets;

import com.tracker.data.Expense;
import com.tracker.data.util.ExpenseData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ari on 8/6/16.
 */
public class ExpenseControllerServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String uri = request.getRequestURI();
        System.out.println("Requested URI: " + uri);

        String jspName = uri.substring(uri.lastIndexOf('/') + 1);
        System.out.println("JSP Name: " + jspName);

        if (jspName.equalsIgnoreCase("viewAllExpenses")) {
            ArrayList<Expense> expenses = ExpenseData.getAllExpenses();
            request.setAttribute("expenses", expenses);
            request.setAttribute("expCategories", Expense.ExpCategory.values());
        } else if (jspName.equalsIgnoreCase("viewCategory")) {
            ArrayList<Expense> expenses = ExpenseData.getExpCategory(Expense.ExpCategory.valueOf(request.getParameter("expCategory")));
            request.setAttribute("expenses", expenses);
            request.setAttribute("expCategories", Expense.ExpCategory.values());
            jspName = "viewAllExpenses";
        } else if (jspName.equalsIgnoreCase("viewExpense")) {
            String expenseId = request.getParameter("id");
            Expense expense = new Expense(Integer.parseInt(expenseId));
            request.setAttribute("expense", expense);
        } else if (jspName.equalsIgnoreCase("addExpense")) {
            request.setAttribute("expCategories",Expense.ExpCategory.values());

        } else if (jspName.equalsIgnoreCase("saveNew")) { //Expense
            Expense expense = new Expense();
            expense.setExpName(request.getParameter("expName"));
            expense.setExpAmount(Integer.parseInt(request.getParameter("expAmount")));
            expense.setExpDate(request.getParameter("expDate"));
            expense.setExpCategory(Expense.ExpCategory.valueOf(request.getParameter("expCategory")));
            expense.saveNew();
            request.setAttribute("expCategories", Expense.ExpCategory.values());
            ArrayList<Expense> expenses = ExpenseData.getAllExpenses();
            request.setAttribute("expenses", expenses);
            jspName = "viewAllExpenses";
        }

        RequestDispatcher view = request.getRequestDispatcher("/home/" + jspName + ".jsp");
        view.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}

