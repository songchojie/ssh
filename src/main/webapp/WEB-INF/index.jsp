<%--
  Created by IntelliJ IDEA.
  User: edianzu
  Date: 2018/9/12
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <table>
        <thead></thead>
        <tbody>
        <c:forEach items="${userList}" var="user">
            <td>${user.name }</td>
        </c:forEach>

        </tbody>
    </table>
</div>

</body>
</html>
