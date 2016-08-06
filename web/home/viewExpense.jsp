<%--
  Created by IntelliJ IDEA.
  User: Ari
  Date: 8/6/16
  Time: 7:03 AM
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
    <title>View Expense</title>
    <link rel="shortcut icon" href="/home/equalSign.png" type="image/x-icon" />
</head>
<body>
<h1>View Expense</h1>
<br><br>
<table>
    <tr>
        <td>Expense ID:</td>
        <td><c:out value="${expense.id}" /></td>
    </tr>
    <tr>
        <td>Expense Name:</td>
        <td><c:out value="${expense.expName}" /></td>
    </tr>
    <tr>
        <td>Expense Amount:</td>
        <td><c:out value="${expense.expAmount}" /></td>
    </tr>
    <tr>
        <td>Expense Date:</td>
        <td><c:out value="${expense.expDate}" /></td>
    </tr>
    <tr>
        <td>Expense Category:</td>
        <td><c:out value="${expense.expCategory}" /></td>
    </tr>
</table>
<br><br>
<a href="/">Home</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/money/viewAllExpenses">View All Expenses</a>
</body>
</html>
