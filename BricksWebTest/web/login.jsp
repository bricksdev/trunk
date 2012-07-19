<%--
    Document   : welcome
    Created on : 2012-3-6, 21:09:41
    Author     : kete
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome to WMS</h1>

        <form name="form1" action="test/welcome.jsp" method="post" style="text-align: center">
            <table width="200" border="0">
                <tr>
                    <td colspan="2">登录窗口</td>
                </tr>
                <tr>
                    <td>用户名</td>
                    <td><input type="text" name="username" size="10"/></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input type="password" name="password" size="10"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" id="login" name="login" value="登录"/> </td>
                </tr>
            </table>
        </form>
    </body>
</html>
