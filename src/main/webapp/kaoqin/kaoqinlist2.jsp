<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

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

                    考勤类型：<select name="leixing">
                    <option value="">所有选项</option>
                    <option value="旷工"
                            <c:if test="${leixing=='旷工' }">selected</c:if> >旷工
                    </option>
                    <option value="迟到"
                            <c:if test="${leixing=='迟到' }">selected</c:if> >迟到
                    </option>
                    <option value="早退"
                            <c:if test="${leixing=='早退' }">selected</c:if> >早退
                    </option>
                    <option value="出差"
                            <c:if test="${leixing=='出差' }">selected</c:if> >出差
                    </option>
                    <option value="请假"
                            <c:if test="${leixing=='请假' }">selected</c:if> >请假
                    </option>
                </select>
                    <input type="submit" value="查询" />
                </form>
            </div>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>

                <TR>
                    <TD align="center">员工姓名</TD>
                    <TD align="center">考勤类型</TD>
                    <TD align="center">日期</TD>
                    <TD align="center">备注</TD>
                    <TD align="center">扣钱（出差类型为加钱）</TD>
                    <TD align="center">添加时间</TD>
                    <TD align="center">
                        操作
                    </TD>
                </TR>
                <c:forEach items="${list}" var="bean">

                    <TR>
                        <TD align="center">${bean.user.truename }</TD>
                        <TD align="center">${bean.leixing }</TD>
                        <TD align="center">${bean.riqi }</TD>
                        <TD align="center">${bean.beizhu }</TD>
                        <TD align="center">${bean.kouqian }</TD>
                        <TD align="center">${fn:substring(bean.createtime,0, 19)}</TD>
                        <TD align="center">
                            <a href="${url2 }update3?id=${bean.id }">查看</a> &nbsp; &nbsp; &nbsp;


                        </TD>
                    </TR>
                </c:forEach>

                <TR>
                    <TD align="center" colspan="21">${pagerinfo }</TD>


                </TR>


            </TABLE>
        </div>
    </body>
</html>

