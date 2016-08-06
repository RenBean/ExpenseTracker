<%--
  Created by IntelliJ IDEA.
  User: Ari
  Date: 8/6/16
  Time: 7:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table.center {
            margin-left:auto;
            margin-right:auto;
        }
        body {text-align:center;
            background-image: url(".");
        }
        *{
            background-color: aliceblue;
            text-align: center;
        }
        a:hover{
            text-decoration: none;
            color:gold;
        }
        a{
            text-decoration: none;
            color:dimgrey;
        }
    </style>
    <title>View Expenses</title>
    <link rel="shortcut icon" href="/home/equalSign.png" type="image/x-icon" />
</head>
<body>
<h1>View Expenses</h1>
<br><br>
<table>
    <tr>
        <th>Expense Id</th>
        <th>Expense Name</th>
        <th>Expense Amount</th>
        <th>Expense Date</th>
        <th>Expense Category</th>
    </tr>
    <c:forEach var="expense" items="${expenses}">
        <tr>
            <td>
                <a href="/money/viewExpense?id=${expense.id}">
                    <c:out value="${expense.id}" />
                </a>
            </td>
            <td><c:out value="${expense.expName}" /></td>
            <td><c:out value="${expense.expAmount}" /></td>
            <td><c:out value="${expense.expDate}" /></td>
            <td><c:out value="${expense.expCategory}" /></td>
        </tr>
    </c:forEach>
</table>
<br><br>
<hr>
<h3>Search By Category</h3>
<form name="categoryForm" method="POST" action="/money/viewCategory">
    Search By Expense Category: <select name="expCategory">
    <c:forEach var="expCategory" items="${expCategories}">
        <option value="<c:out value="${expCategory}"/>"><c:out value="${expCategory}"/></option>
    </c:forEach>
</select><br>
    <input type="submit">
</form>

<a href="/">Home</a>
<br>
<a href="/money/addExpense">Add Expense</a><br>
<br>
<a href="/money/viewAllExpenses">View All Expenses</a><br>
</body>
</html>