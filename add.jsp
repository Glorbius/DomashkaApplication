<%@ page contentType="text/html;charset=utf-8" %>
<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");
String age = request.getParameter("age");
String legsize = request.getParameter("legsize");
String gender = request.getParameter("gender");
String status = request.getParameter("status");
String uspeh = "Вы успешно зарегистрированы!";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta pageEncoding = "utf-8" charset="windows-1251" />
        <title>Успешная регистрация</title>
        <link href="styleIndexJsp.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h4 id="name">Имя: ${name} </h4>
    </body>
</html>
