<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/template.tld" prefix="h"%>
<!DOCTYPE html>
<html >

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/common.css"  />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/calendar.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/AJaxUtil.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/common.js"></script>
        <h:title />
    </head>
    <body >

        <div id="header" >
            <%@include file="Top.jsp" %>
        </div>

        <div id="menuBar" class="menu">
            <h:MenuNav />
        </div>
       <div id="taskbox" class="task">

            <h:Tasks />
        </div>

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
