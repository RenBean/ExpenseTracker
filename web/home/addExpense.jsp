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
    <title>Add Expense</title>
    <link rel="shortcut icon" href="/home/equalSign.png" type="image/x-icon" />
</head>
<body>
<h1>Add Expense</h1>
<br><br>
<form name="expForm" method="POST" action="/money/saveNew">
    Expense Name: <input type="text" name="expName" /><br>
    Expense Amount: <input type="text" name="expAmount" /><br>
    Expense Date: <input type="date" name="expDate"/><br>

    Expense Category: <select name="expCategory">
    <c:forEach var="expCategory" items="${expCategories}">
        <option value="<c:out value="${expCategory}"/>"><c:out value="${expCategory}"/> </option>
    </c:forEach><br>
</select><br>



    <input type="submit">
</form>
</body>
</html>
