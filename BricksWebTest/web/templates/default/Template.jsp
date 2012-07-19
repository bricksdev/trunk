<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/template.tld" prefix="h"%>

<!DOCTYPE html>
<html >

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/common.css"  />
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/float_menu.css"  />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/calendar.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/AJaxUtil.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/common.js"></script>
        <title><h:title /></title>

    </head>
    <body onload="selectedStyle('${__style}');">

        <div id="header" >
            <%@include file="Top.jsp" %>
        </div>

        <div id="menuBar" class="menu">
            <h:MenuNav />
        </div>
        <div id="taskbox" class="task">

            <h:Tasks />
        </div>
        <form >
            <select id="__style" name="__style" onchange="this.form.submit();">
                <option value="DEFAULT" checked >默认风格</option>
                <option value="WMS" >WMS风格</option>
            </select>
        </form>
        <div id="content">
            <div id="message" class="message">
                <div id="infoDiv" style="color:green;">
                    ${pageContext.findAttribute("info")}
                </div>
                <div id="errorDiv" style="color:red">
                    ${pageContext.findAttribute("error")}
                </div>
            </div>
            <h:content/>
        </div>


        <div id="footer">
            <%@include file="footer.jsp" %>
        </div>
    </body>

</html>
