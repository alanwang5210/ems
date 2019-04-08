<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Untitled Document</title>
        <style type="text/css">
            body {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12px;
                text-align: center;
            }

            a {
                text-decoration: none;
            }

            #all {
                widht: 100%;
                text-align: center;
                margin: auto;
            }

            #main {
                width: 96%;
                line-height: 1.8;
                margin: auto;
                border: 1px #ccc dashed;
                text-align: left;
                text-indent: 2em;
            }
        </style>

    </head>

    <body>
        <div id="all">
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
                <TR height=28>
                    <TD background=images/title_bg1.jpg>当前位置:---》》${title }</TD>
                </TR>
                <TR>
                    <TD bgColor=#b1ceef height=1></TD>
                </TR>
                <TR height=20>
                    <TD background=images/shadow_bg.jpg></TD>
                </TR>
            </TABLE>
            <div align="left">
                <form action="${url }" method="post">

                    月份：<input name="yuefen" type="text" value="${yuefen }">
                    <input type="submit" value="查询" />
                </form>
            </div>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>

                <TR>
                    <TD align="center">部门名</TD>
                    <TD align="center">员工姓名</TD>
                    <TD align="center">职务</TD>
                    <TD align="center">月份</TD>
                    <TD align="center">基本工资</TD>
                    <TD align="center">请假</TD>
                    <TD align="center">旷工</TD>
                    <TD align="center">迟到</TD>
                    <TD align="center">早退</TD>
                    <TD align="center">出差</TD>
                    <TD align="center">奖金</TD>
                    <TD align="center">实发总计</TD>

                </TR>
                <c:forEach items="${list}" var="bean">

                    <TR>
                        <TD align="center">${bean.user.bumen.name }</TD>
                        <TD align="center">${bean.user.truename }</TD>
                        <TD align="center">${bean.user.zhiwu }</TD>
                        <TD align="center">${bean.yuefen }</TD>
                        <TD align="center">${bean.jibengongzi }</TD>
                        <TD align="center">-${bean.qingjia }</TD>
                        <TD align="center">-${bean.kuangong }</TD>
                        <TD align="center">-${bean.chidao }</TD>
                        <TD align="center">-${bean.zaotui }</TD>
                        <TD align="center">${bean.chuchai }</TD>
                        <TD align="center">${bean.jiangjin }</TD>
                        <TD align="center">${bean.zongji }</TD>


                    </TR>
                </c:forEach>

                <TR>
                    <TD align="center" colspan="21">${pagerinfo }</TD>


                </TR>


            </TABLE>
        </div>
    </body>
</html>

