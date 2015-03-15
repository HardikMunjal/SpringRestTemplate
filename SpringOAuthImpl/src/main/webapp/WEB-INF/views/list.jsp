<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>List of Users</title>

</head>

<body>

<table border="1" align="center" style="width:50%">

        <thead>

            <tr>

                <th>SKU</th>

                <th>Price</th>

                

            </tr>

        </thead>

        <tbody>

            <c:forEach var="catalog" items="${catalog}" >

                <tr>

                    <td>${catalog.sku}</td>

                    <td>${catalog.price}</td>
                    
                    <td><button>update</button>

                    
                </tr>

            </c:forEach>

        </tbody>

    </table>

</body>

</html>
