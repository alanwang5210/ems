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
        <script language="javascript" type="text/javascript" src="js/showdate.js"></script>

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
            <form action="${url }?id=${bean.id }" method="post" onsubmit="return checkform() "
                  enctype="multipart/form-data">
                <TABLE cellSpacing=0 cellPadding=0 width="60%" align=center border=1>

                    <TR height=>
                        <TD align="center">用户名:</TD>
                        <TD align="center">
                            ${bean.username }

                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">密码:</TD>
                        <TD align="center">
                            ${bean.password }

                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">真实姓名：</TD>
                        <TD align="center">
                            ${bean.truename }

                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">入职时间：</TD>
                        <TD align="center">
                            ${bean.ruzhishijian }
                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">所属部门：</TD>
                        <TD align="center">
                            ${bean.bumen.name}

                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">职务：</TD>
                        <TD align="center">
                            ${bean.zhiwu }

                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">员工形象：</TD>
                        <TD align="center">
                            <img src="<%=basePath %>uploadfile/${bean.xingxiang }" width="200" height="200" />

                        </TD>
                    </TR>


                    <TR height=>
                        <TD align="center">性别：</TD>
                        <TD align="center">
                            ${bean.xingbie}

                        </TD>
                    </TR>


                    <TR height=>
                        <TD align="center">地址：</TD>
                        <TD align="center">
                            ${bean.dizhi }
                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">联系方式：</TD>
                        <TD align="center">
                            ${bean.lianxifangshi }
                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">文化程度：</TD>
                        <TD align="center">
                            ${bean.wenhuachengdu }
                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">政治面貌：</TD>
                        <TD align="center">
                            ${bean.zhengzhimianmao }
                        </TD>
                    </TR>

                    <TR height=>
                        <TD align="center">籍贯：</TD>
                        <TD align="center">
                            ${bean.jiguan }
                        </TD>
                    </TR>


                    <TR height=>
                        <TD align="center"> 操作：</TD>
                        <TD align="center">

                            <input onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />

                        </TD>
                    </TR>

                </TABLE>
            </form>
        </div>
    </body>
</html>

