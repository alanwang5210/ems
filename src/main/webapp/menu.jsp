<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>无标题文档</title>
        <style type="text/css">
            html, body {
                height: 100%;
                overflow: hidden;
            }

            /*为兼容ie7,ff*/
            body {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                margin: 0px;
                text-align: center;
                border-right: 1px #ccc solid;
            }

            a {
                color: #000;
                text-decoration: none;
            }

            #menu img {
                _margin-top: 12px;
            }

            /*没办法,ie6对list-style-image支持不好*/
            #all {
                width: 100%;
                height: 100%;
            }

            #menu {
                width: 96%;
            }

            #menu ul {
                padding: 0;
                margin: 0;
                list-style: none;
            }

            #menu ul li {
                background-image: url(/images/menu_bg.gif);
                background-repeat: repeat-x;
                background-position: center;
                height: 32px;;
                margin-top: 2px;
                margin-bottom: 2px;
                border: 1px #ccc solid;
                line-height: 2.8;
            }
        </style>
    </head>

    <body>
        <div id="all">
            <div id="menu">
                <ul>
                    <c:if test="${user.role==1}">
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!bumenlist" target="main">部门管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!userlist" target="main">员工管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!qingjialist2" target="main">请假审核管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!kaoqinlist" target="main">员工考勤管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!jiangjinlist" target="main">员工奖金管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!gongzilist" target="main">员工工资管理</a></li>

                    </c:if>
                    <c:if test="${user.role==0}">
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!qingjialist" target="main">请假管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!userlist2" target="main">个人基本信息管理</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!kaoqinlist2" target="main">个人考勤查询</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!jiangjinlist2" target="main">个人奖金查询</a></li>
                        <li><img src="images/li.jpg" />&nbsp;&nbsp;&nbsp; <a
                                href="method!gongzilist2" target="main">个人工资查询</a></li>

                    </c:if>
                </ul>
            </div>
        </div>
    </body>
</html>


