<%@taglib uri="/WEB-INF/tlds/template.tld" prefix="h"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%--<%@ include file="/views/appinfo/Language.jspf"%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="${pageContext.servletContext.contextPath}/public/ins/bydicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/public/ins/bydicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/css/vmi_global.css"  type="text/css" />
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/css/input_table.css"  type="text/css" />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/js/vmi_menu.js"></script>     <!--导航交互js文件-->
        <link type="text/css" rel="stylesheet" href="${pageContext.servletContext.contextPath}/public/bricks/css/common.css"  />
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/calendar.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/AJaxUtil.js"></script>
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/public/bricks/js/1_common.js"></script>
        <title>BPS</title>
    </head>


    <body onload="setTab('two',1,8)">

        <div id="vmi_global_content">

            <jsp:include page="Top.jsp"/>
            <div id="vmi_global_content">
            <div class="vmi_content_title">
                <div class="vmi_content_title_a">当前位置 - 出仓管理 ><span class="vmi_title_a"> 计划出库 > 生产订单领料</span></div>

                <div class="vmi_content_title_b"></div>
            </div>
            <h:content/>
            </div>

            <jsp:include page="Footer.jsp"/>


        </div>


    </body>

</html>
