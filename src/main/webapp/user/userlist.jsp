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
                    <a href="${url2 }add"><span style="font-size: 25px;font-weight: bold;">添加新员工</span></a>
                    用户名：<input name="username" type="text" value="${username }">
                    真实姓名：<input name="truename" type="text" value="${truename }">
                    部门：<select name="bumen">
                    <option value="">所有选项</option>
                    <c:forEach items="${bumenlist}" var="bean2">
                        <option value="${bean2.name }"
                                <c:if test="${bumen==bean2.name }">selected</c:if> >${bean2.name }</option>
                    </c:forEach>
                </select>
                    <input type="submit" value="查询" />
                </form>
            </div>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=1>

                <TR>
                    <TD align="center">用户名</TD>
                    <TD align="center">密码</TD>
                    <TD align="center">真实姓名</TD>
                    <TD align="center">所属部门</TD>
                    <TD align="center">职务</TD>

                    <TD align="center">
                        操作
                    </TD>
                </TR>
                <c:forEach items="${list}" var="bean">

                    <TR>
                        <TD align="center">${bean.username }</TD>
                        <TD align="center">${bean.password }</TD>
                        <TD align="center">${bean.truename }</TD>
                        <TD align="center">${bean.bumen.name }</TD>
                        <TD align="center">${bean.zhiwu }</TD>
                        <TD align="center">
                            <a href="${url2 }update3?id=${bean.id }">查看</a> &nbsp; &nbsp; &nbsp;
                            <a href="${url2 }update?id=${bean.id }">修改</a> &nbsp; &nbsp; &nbsp;
                            <a href="${url2 }delete?id=${bean.id }">删除</a>

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

