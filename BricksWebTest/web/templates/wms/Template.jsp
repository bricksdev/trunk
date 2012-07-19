<%@taglib uri="/WEB-INF/tlds/template.tld" prefix="h"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@ include file="/views/appinfo/Language.jspf"%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;"/>
        <link rel="icon" href="${pageContext.servletContext.contextPath}/public/ins/bydicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/public/ins/bydicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/css/vmi_global.css"  type="text/css" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/css/input_table.css"  type="text/css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/float_menu.css"  />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/js/vmi_menu.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/common.css"  />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/calendar.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/AJaxUtil.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/common.js"></script>
        <title><h:title /></title>
    </head>

    <body onload="setTab('two',1,8);selectedStyle('${__style}');">

        <div >
            <jsp:include page="Top.jsp"/>
            <div id="taskbox" class="task" style="padding-left: 5px">

                <h:Tasks />
            </div>
        <form >
            <select id="__style" name="__style" onchange="this.form.submit();">
                <option value="DEFAULT"  >默认风格</option>
                <option value="WMS" >WMS风格</option>
            </select>
        </form>
            <div id="vmi_global_content">
                <div class="vmi_content_title">
                    <div class="vmi_content_title_a"><span class="vmi_title_a"></span></div>

                    <div class="vmi_content_title_b"></div>
                </div>
                <div id="message" class="message">
                    <div id="infoDiv" style="color:green;">
                        ${pageContext.findAttribute("info")}
                    </div>
                    <div id="errorDiv" style="color:red">
                        ${pageContext.findAttribute("error")}
                    </div>
                </div>
                <h:content/>
                <jsp:include page="Footer.jsp"/>
            </div>




        </div>


    </body>

</html>
