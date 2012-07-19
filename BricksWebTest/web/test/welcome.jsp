<%--
    Document   : index
    Created on : 2012-2-3, 14:09:52
    Author     : kete
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <a href="${pageContext.servletContext.contextPath}/test/EditStory.jsp" >testStory</a>

        <a href="${pageContext.servletContext.contextPath}/test/ViewStory.jsp">ViewStory</a>

        <a href="${pageContext.servletContext.contextPath}/receipt/Receipt.jsp" >Receipt</a>

        <a href="${pageContext.servletContext.contextPath}/inspect/ViewInspect.jsp" >Inspect</a>
    </body>
</html>
