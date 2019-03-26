package com.xxs.ems.action;

import com.opensymphony.xwork2.ActionSupport;
import com.xxs.ems.dao.*;
import com.xxs.ems.model.*;
import com.xxs.ems.util.Arith;
import com.xxs.ems.util.Pager;
import com.xxs.ems.util.Util;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ManageAction extends ActionSupport {

    private static final long serialVersionUID = -4304509122548259589L;
    private UserDao userDao;
    private String url = "./";
    private BumenDao bumenDao;
    private File uploadfile;
    private QingjiaDao qingjiaDao;
    private KaoqinDao kaoqinDao;
    private JiangjinDao jiangjinDao;
    private GongziDao gongziDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //login
    public String login() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        User user = userDao.selectBean(" where username = '" + username
                + "' and password= '" + password + "' and role= " + role + " and deletestatus=0 ");
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            this.setUrl("index.jsp");
            return "redirect";
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
        }
        return null;
    }

    //用户退出
    public String loginout() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        this.setUrl("login.jsp");
        return SUCCESS;
    }

    //跳转到修改密码页面
    public String changepwd() {
        this.setUrl("user/user.jsp");
        return SUCCESS;
    }

    //修改密码操作
    public void changepwd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        User bean = userDao.selectBean(" where username= '" + u.getUsername() + "' and password= '" + password1 + "' and deletestatus=0");
        if (bean != null) {
            bean.setPassword(password2);
            userDao.updateBean(bean);
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('修改成功');</script>");
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('用户名或者密码错误');</script>");
        }
    }

    public BumenDao getBumenDao() {
        return bumenDao;
    }

    public void setBumenDao(BumenDao bumenDao) {
        this.bumenDao = bumenDao;
    }

    //部门列表
    public String bumenlist() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");

        if (name != null && !"".equals(name)) {

            sb.append("name like '%" + name + "%'");
            sb.append(" and ");
            request.setAttribute("name", name);
        }


        sb.append("  deletestatus=0 order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = bumenDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", bumenDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!bumenlist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!bumenlist");
        request.setAttribute("url2", "method!bumen");
        request.setAttribute("title", "部门管理");
        this.setUrl("bumen/bumenlist.jsp");
        return SUCCESS;

    }

    //跳转到添加部门页面
    public String bumenadd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("url", "method!bumenadd2");
        request.setAttribute("title", "部门添加");
        this.setUrl("bumen/bumenadd.jsp");
        return SUCCESS;
    }

    //添加部门操作
    public void bumenadd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("name");
        String jibengongzi = request.getParameter("jibengongzi");

        Bumen bean = bumenDao.selectBean(" where name='" + name + "' and deletestatus=0 ");
        if (bean == null) {
            bean = new Bumen();
            bean.setName(name);
            bean.setJibengongzi(jibengongzi);
            bumenDao.insertBean(bean);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('操作失败，该部门名已经存在');window.location.href='method!bumenlist';</script>");
        }
    }

    //跳转到更新部门页面
    public String bumenupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Bumen bean = bumenDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("url", "method!bumenupdate2");
        request.setAttribute("title", "部门修改");
        this.setUrl("bumen/bumenupdate.jsp");
        return SUCCESS;
    }

    //更新部门操作
    public void bumenupdate2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jibengongzi = request.getParameter("jibengongzi");

        Bumen bean = bumenDao.selectBean(" where id= "
                + request.getParameter("id"));

        bean.setJibengongzi(jibengongzi);
        bumenDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");
    }

    //删除部门操作
    public void bumendelete() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Bumen bean = bumenDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setDeletestatus(1);
        bumenDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!bumenlist';</script>");
    }

    //跳转到查看部门页面
    public String bumenupdate3() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Bumen bean = bumenDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("title", "部门查看");
        this.setUrl("bumen/bumenupdate3.jsp");
        return SUCCESS;
    }

    //员工列表
    public String userlist() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");
        String bumen = request.getParameter("bumen");
        String truename = request.getParameter("truename");
        request.setAttribute("bumenlist", bumenDao.selectBeanList(0, 9999, " where deletestatus=0 "));

        StringBuffer sb = new StringBuffer();
        sb.append(" where ");

        if (username != null && !"".equals(username)) {

            sb.append("username like '%" + username + "%'");
            sb.append(" and ");
            request.setAttribute("username", username);
        }
        if (bumen != null && !"".equals(bumen)) {

            sb.append("bumen.name like '%" + bumen + "%'");
            sb.append(" and ");
            request.setAttribute("bumen", bumen);
        }
        if (truename != null && !"".equals(truename)) {

            sb.append("truename like '%" + truename + "%'");
            sb.append(" and ");
            request.setAttribute("truename", truename);
        }


        sb.append("  deletestatus=0 and role=0 order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = userDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!userlist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!userlist");
        request.setAttribute("url2", "method!user");
        request.setAttribute("title", "员工管理");
        this.setUrl("user/userlist.jsp");
        return SUCCESS;

    }

    public File getUploadfile() {
        return uploadfile;
    }

    public void setUploadfile(File uploadfile) {
        this.uploadfile = uploadfile;
    }

    //跳转到添加员工页面
    public String useradd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("url", "method!useradd2");
        request.setAttribute("bumenlist", bumenDao.selectBeanList(0, 9999, " where deletestatus=0 "));
        request.setAttribute("title", "员工添加");
        this.setUrl("user/useradd.jsp");
        return SUCCESS;
    }

    //添加员工操作
    public void useradd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String username = request.getParameter("username");


        User bean = userDao.selectBean(" where username='" + username + "' and deletestatus=0 ");
        if (bean == null) {
            String bumen = request.getParameter("bumen");
            String dizhi = request.getParameter("dizhi");
            String jiguan = request.getParameter("jiguan");
            String lianxifangshi = request.getParameter("lianxifangshi");
            String ruzhishijian = request.getParameter("ruzhishijian");
            String truename = request.getParameter("truename");
            String wenhuachengdu = request.getParameter("wenhuachengdu");
            String xingbie = request.getParameter("xingbie");

            String zhengzhimianmao = request.getParameter("zhengzhimianmao");
            String zhiwu = request.getParameter("zhiwu");
            String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
            String time = Util.getTime2();
            String imgpath = time + ".jpg";
            File file = new File(savapath + imgpath);
            Util.copyFile(uploadfile, file);
            bean = new User();
            bean.setBumen(bumenDao.selectBean(" where id= " + bumen));
            bean.setCreatetime(new Date());
            bean.setDizhi(dizhi);
            bean.setJiguan(jiguan);
            bean.setLianxifangshi(lianxifangshi);
            bean.setPassword("111111");
            bean.setRole(0);
            bean.setRuzhishijian(ruzhishijian);
            bean.setTruename(truename);
            bean.setUsername(username);
            bean.setWenhuachengdu(wenhuachengdu);
            bean.setXingbie(xingbie);
            bean.setXingxiang(imgpath);
            bean.setZhengzhimianmao(zhengzhimianmao);
            bean.setZhiwu(zhiwu);
            userDao.insertBean(bean);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
        } else {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("gbk");
            response.setContentType("text/html; charset=gbk");
            response
                    .getWriter()
                    .print(
                            "<script language=javascript>alert('操作失败，该用户名已经存在');window.location.href='method!userlist';</script>");
        }
    }

    //跳转到更新员工页面
    public String userupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        User bean = userDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("bumenlist", bumenDao.selectBeanList(0, 9999, " where deletestatus=0 "));
        request.setAttribute("url", "method!userupdate2");
        request.setAttribute("title", "员工修改");
        this.setUrl("user/userupdate.jsp");
        return SUCCESS;
    }

    //更新员工操作
    public void userupdate2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();


        String bumen = request.getParameter("bumen");
        String dizhi = request.getParameter("dizhi");
        String jiguan = request.getParameter("jiguan");
        String lianxifangshi = request.getParameter("lianxifangshi");
        String ruzhishijian = request.getParameter("ruzhishijian");
        String truename = request.getParameter("truename");
        String wenhuachengdu = request.getParameter("wenhuachengdu");
        String xingbie = request.getParameter("xingbie");

        String zhengzhimianmao = request.getParameter("zhengzhimianmao");
        String zhiwu = request.getParameter("zhiwu");

        User bean = userDao.selectBean(" where id= "
                + request.getParameter("id"));
        if (uploadfile != null) {
            String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
            String time = Util.getTime2();
            String imgpath = time + ".jpg";
            File file = new File(savapath + imgpath);
            Util.copyFile(uploadfile, file);
            bean.setXingxiang(imgpath);
        }
        bean.setBumen(bumenDao.selectBean(" where id= " + bumen));
        bean.setDizhi(dizhi);
        bean.setJiguan(jiguan);
        bean.setLianxifangshi(lianxifangshi);
        bean.setRuzhishijian(ruzhishijian);
        bean.setTruename(truename);
        bean.setWenhuachengdu(wenhuachengdu);
        bean.setXingbie(xingbie);
        bean.setZhengzhimianmao(zhengzhimianmao);
        bean.setZhiwu(zhiwu);
        userDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
    }

    //删除员工操作
    public void userdelete() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        User bean = userDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setDeletestatus(1);
        userDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
    }

    //跳转到查看员工页面
    public String userupdate3() {
        HttpServletRequest request = ServletActionContext.getRequest();
        User bean = userDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("title", "员工查看");
        this.setUrl("user/userupdate3.jsp");
        return SUCCESS;
    }

    public QingjiaDao getQingjiaDao() {
        return qingjiaDao;
    }

    public void setQingjiaDao(QingjiaDao qingjiaDao) {
        this.qingjiaDao = qingjiaDao;
    }

    //请假列表
    public String qingjialist() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String shenhe = request.getParameter("shenhe");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (shenhe != null && !"".equals(shenhe)) {

            sb.append(" shenhe like '%" + shenhe + "%'");
            sb.append(" and ");
            request.setAttribute("shenhe", shenhe);
        }


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        sb.append("  deletestatus=0  and user.id=" + user.getId() + " order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = qingjiaDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", qingjiaDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!qingjialist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!qingjialist");
        request.setAttribute("url2", "method!qingjia");
        request.setAttribute("title", "请假管理");
        this.setUrl("qingjia/qingjialist.jsp");
        return SUCCESS;

    }

    //跳转到添加请假页面
    public String qingjiaadd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("url", "method!qingjiaadd2");
        request.setAttribute("userlist", userDao.selectBeanList(0, 9999, " where deletestatus=0 and role=0 "));
        request.setAttribute("title", "请假添加");
        this.setUrl("qingjia/qingjiaadd.jsp");
        return SUCCESS;
    }

    //添加请假操作
    public void qingjiaadd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String content = request.getParameter("content");
        String qingjiariqi = request.getParameter("qingjiariqi");


        Qingjia bean = new Qingjia();
        bean.setCreatetime(new Date());
        bean.setContent(content);
        bean.setCreatetime(new Date());
        bean.setQingjiariqi(qingjiariqi);
        bean.setShenhe("未审核");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        bean.setUser(user);
        qingjiaDao.insertBean(bean);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!qingjialist';</script>");
    }

    //删除请假操作
    public void qingjiadelete() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Qingjia bean = qingjiaDao.selectBean(" where id= "
                + request.getParameter("id"));
        if ("未审核".equals(bean.getShenhe())) {
            bean.setDeletestatus(1);
        }
        qingjiaDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!qingjialist';</script>");
    }

    //跳转到查看请假页面
    public String qingjiaupdate3() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Qingjia bean = qingjiaDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("title", "请假查看");
        this.setUrl("qingjia/qingjiaupdate3.jsp");
        return SUCCESS;
    }

    //请假审核列表
    public String qingjialist2() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String shenhe = request.getParameter("shenhe");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (shenhe != null && !"".equals(shenhe)) {

            sb.append(" shenhe like '%" + shenhe + "%'");
            sb.append(" and ");
            request.setAttribute("shenhe", shenhe);
        }


        sb.append("  deletestatus=0   order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = qingjiaDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", qingjiaDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!qingjialist2", "共有" + total + "条记录"));
        request.setAttribute("url", "method!qingjialist2");
        request.setAttribute("url2", "method!qingjia");
        request.setAttribute("title", "请假管理");
        this.setUrl("qingjia/qingjialist2.jsp");
        return SUCCESS;

    }

    //审核通过请假操作
    public void qingjiadelete2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Qingjia bean = qingjiaDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setShenhe("审核通过");
        qingjiaDao.updateBean(bean);

        Kaoqin k = new Kaoqin();
        k.setBeizhu("请假审核通过");
        k.setCreatetime(new Date());
        k.setKouqian("50");
        k.setLeixing("请假");
        k.setRiqi(bean.getQingjiariqi());
        k.setUser(bean.getUser());
        kaoqinDao.insertBean(k);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!qingjialist2';</script>");
    }

    //审核不通过请假操作
    public void qingjiadelete3() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Qingjia bean = qingjiaDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setShenhe("审核不通过");
        qingjiaDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!qingjialist2';</script>");
    }

    public KaoqinDao getKaoqinDao() {
        return kaoqinDao;
    }

    public void setKaoqinDao(KaoqinDao kaoqinDao) {
        this.kaoqinDao = kaoqinDao;
    }

    //考勤列表
    public String kaoqinlist() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String truename = request.getParameter("truename");
        String leixing = request.getParameter("leixing");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (truename != null && !"".equals(truename)) {

            sb.append("user.truename like '%" + truename + "%'");
            sb.append(" and ");
            request.setAttribute("truename", truename);
        }

        if (leixing != null && !"".equals(leixing)) {

            sb.append("leixing like '%" + leixing + "%'");
            sb.append(" and ");
            request.setAttribute("leixing", leixing);
        }


        sb.append("  deletestatus=0 order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = kaoqinDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", kaoqinDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!kaoqinlist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!kaoqinlist");
        request.setAttribute("url2", "method!kaoqin");
        request.setAttribute("title", "考勤管理");
        this.setUrl("kaoqin/kaoqinlist.jsp");
        return SUCCESS;

    }

    //跳转到添加考勤页面
    public String kaoqinadd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("url", "method!kaoqinadd2");
        request.setAttribute("userlist", userDao.selectBeanList(0, 9999, " where deletestatus=0 and role=0 "));
        request.setAttribute("title", "考勤添加");
        this.setUrl("kaoqin/kaoqinadd.jsp");
        return SUCCESS;
    }

    //添加考勤操作
    public void kaoqinadd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String beizhu = request.getParameter("beizhu");
        String kouqian = request.getParameter("kouqian");
        String leixing = request.getParameter("leixing");
        String riqi = request.getParameter("riqi");
        String user = request.getParameter("user");

        Kaoqin bean = new Kaoqin();
        bean.setCreatetime(new Date());
        bean.setBeizhu(beizhu);
        bean.setKouqian(kouqian);
        bean.setLeixing(leixing);
        bean.setRiqi(riqi);

        bean.setUser(userDao.selectBean(" where id= " + user));
        kaoqinDao.insertBean(bean);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");
    }

    //跳转到更新考勤页面
    public String kaoqinupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Kaoqin bean = kaoqinDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("url", "method!kaoqinupdate2");
        request.setAttribute("title", "考勤修改");
        this.setUrl("kaoqin/kaoqinupdate.jsp");
        return SUCCESS;
    }

    //更新考勤操作
    public void kaoqinupdate2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();


        String beizhu = request.getParameter("beizhu");
        String kouqian = request.getParameter("kouqian");

        Kaoqin bean = kaoqinDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setBeizhu(beizhu);
        bean.setKouqian(kouqian);
        kaoqinDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");
    }

    //删除考勤操作
    public void kaoqindelete() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Kaoqin bean = kaoqinDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setDeletestatus(1);
        kaoqinDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!kaoqinlist';</script>");
    }

    //跳转到查看考勤页面
    public String kaoqinupdate3() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Kaoqin bean = kaoqinDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("title", "考勤查看");
        this.setUrl("kaoqin/kaoqinupdate3.jsp");
        return SUCCESS;
    }

    public JiangjinDao getJiangjinDao() {
        return jiangjinDao;
    }

    public void setJiangjinDao(JiangjinDao jiangjinDao) {
        this.jiangjinDao = jiangjinDao;
    }

    //奖金列表
    public String jiangjinlist() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String truename = request.getParameter("truename");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (truename != null && !"".equals(truename)) {

            sb.append("user.truename like '%" + truename + "%'");
            sb.append(" and ");
            request.setAttribute("truename", truename);
        }


        sb.append("  deletestatus=0 order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = jiangjinDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", jiangjinDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!jiangjinlist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!jiangjinlist");
        request.setAttribute("url2", "method!jiangjin");
        request.setAttribute("title", "奖金管理");
        this.setUrl("jiangjin/jiangjinlist.jsp");
        return SUCCESS;

    }

    //跳转到添加奖金页面
    public String jiangjinadd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("url", "method!jiangjinadd2");
        request.setAttribute("userlist", userDao.selectBeanList(0, 9999, " where deletestatus=0 and role=0 "));
        request.setAttribute("title", "奖金添加");
        this.setUrl("jiangjin/jiangjinadd.jsp");
        return SUCCESS;
    }

    //添加奖金操作
    public void jiangjinadd2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        String content = request.getParameter("content");
        String jine = request.getParameter("jine");
        String riqi = request.getParameter("riqi");
        String user = request.getParameter("user");

        Jiangjin bean = new Jiangjin();
        bean.setContent(content);
        bean.setCreatetime(new Date());
        bean.setJine(jine);
        bean.setRiqi(riqi);

        bean.setUser(userDao.selectBean(" where id= " + user));
        jiangjinDao.insertBean(bean);

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!jiangjinlist';</script>");
    }

    //跳转到更新奖金页面
    public String jiangjinupdate() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Jiangjin bean = jiangjinDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("url", "method!jiangjinupdate2");
        request.setAttribute("title", "奖金修改");
        this.setUrl("jiangjin/jiangjinupdate.jsp");
        return SUCCESS;
    }

    //更新奖金操作
    public void jiangjinupdate2() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();


        String content = request.getParameter("content");
        String jine = request.getParameter("jine");

        Jiangjin bean = jiangjinDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setContent(content);
        bean.setJine(jine);
        jiangjinDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!jiangjinlist';</script>");
    }

    //删除奖金操作
    public void jiangjindelete() throws IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        Jiangjin bean = jiangjinDao.selectBean(" where id= "
                + request.getParameter("id"));
        bean.setDeletestatus(1);
        jiangjinDao.updateBean(bean);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!jiangjinlist';</script>");
    }

    //跳转到查看奖金页面
    public String jiangjinupdate3() {
        HttpServletRequest request = ServletActionContext.getRequest();
        Jiangjin bean = jiangjinDao.selectBean(" where id= "
                + request.getParameter("id"));
        request.setAttribute("bean", bean);
        request.setAttribute("title", "奖金查看");
        this.setUrl("jiangjin/jiangjinupdate3.jsp");
        return SUCCESS;
    }

    public GongziDao getGongziDao() {
        return gongziDao;
    }

    public void setGongziDao(GongziDao gongziDao) {
        this.gongziDao = gongziDao;
    }

    //工资列表
    public String gongzilist() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String truename = request.getParameter("truename");

        String yuefen = request.getParameter("yuefen");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (truename != null && !"".equals(truename)) {

            sb.append("user.truename like '%" + truename + "%'");
            sb.append(" and ");
            request.setAttribute("truename", truename);
        }

        if (yuefen != null && !"".equals(yuefen)) {

            sb.append("yuefen like '%" + yuefen + "%'");
            sb.append(" and ");
            request.setAttribute("yuefen", yuefen);
        }


        sb.append("  1=1 order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = gongziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", gongziDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!gongzilist", "共有" + total + "条记录"));
        request.setAttribute("url", "method!gongzilist");
        request.setAttribute("url2", "method!gongzi");
        request.setAttribute("title", "工资管理");
        this.setUrl("gongzi/gongzilist.jsp");
        return SUCCESS;

    }


    //自动生成工资操作
    public void gongzidelete() throws IOException {
        String yuefen = Util.getYuefen();

        List<Gongzi> list = gongziDao.selectBeanList(0, 99999, " where yuefen='" + yuefen + "' ");
        for (Gongzi bean : list) {
            gongziDao.deleteBean(bean);
        }
        List<User> list2 = userDao.selectBeanList(0, 99999, " where deletestatus=0 and role=0 ");
        for (User user : list2) {
            List<Kaoqin> list3 = kaoqinDao.selectBeanList(0, 9999, " where   riqi like '%" + yuefen + "%'  and deletestatus=0 and user.id= " + user.getId());
            List<Jiangjin> list4 = jiangjinDao.selectBeanList(0, 9999, " where  riqi like '%" + yuefen + "%' and deletestatus=0 and user.id= " + user.getId());
            Gongzi bean = new Gongzi();
            bean.setUser(user);
            bean.setYuefen(yuefen);

            if ("普通员工".equals(user.getZhiwu())) {
                bean.setJibengongzi(user.getBumen().getJibengongzi());
            } else if ("部门经理".equals(user.getZhiwu())) {
                double jine = Arith.add(Double.parseDouble(user.getBumen().getJibengongzi()), 2000);
                bean.setJibengongzi(jine + "");
            }
            bean.setKuangong("0");
            bean.setQingjia("0");
            bean.setChidao("0");
            bean.setZaotui("0");
            bean.setChuchai("0");

            for (Kaoqin k : list3) {
                if ("旷工".equals(k.getLeixing())) {
                    double jine = Arith.add(Double.parseDouble(bean.getKuangong()), Double.parseDouble(k.getKouqian()));
                    bean.setKuangong(jine + "");
                }
                if ("请假".equals(k.getLeixing())) {
                    double jine = Arith.add(Double.parseDouble(bean.getQingjia()), Double.parseDouble(k.getKouqian()));
                    bean.setQingjia(jine + "");
                }
                if ("迟到".equals(k.getLeixing())) {
                    double jine = Arith.add(Double.parseDouble(bean.getChidao()), Double.parseDouble(k.getKouqian()));
                    bean.setChidao(jine + "");
                }
                if ("早退".equals(k.getLeixing())) {
                    double jine = Arith.add(Double.parseDouble(bean.getZaotui()), Double.parseDouble(k.getKouqian()));
                    bean.setZaotui(jine + "");
                }
                if ("出差".equals(k.getLeixing())) {
                    double jine = Arith.add(Double.parseDouble(bean.getChuchai()), Double.parseDouble(k.getKouqian()));
                    bean.setChuchai(jine + "");
                }
            }

            bean.setJiangjin("0");
            for (Jiangjin j : list4) {
                double jine = Arith.add(Double.parseDouble(bean.getJiangjin()), Double.parseDouble(j.getJine()));
                bean.setJiangjin(jine + "");
            }

            double t1 = Arith.add(Double.parseDouble(bean.getJiangjin()), Double.parseDouble(bean.getJibengongzi()));//奖金+基本工资
            double t2 = Arith.add(Double.parseDouble(bean.getChuchai()), t1);//在加上出差工资

            double t3 = Arith.add(Double.parseDouble(bean.getKuangong()), Double.parseDouble(bean.getQingjia()));//旷工+请假
            double t4 = Arith.add(Double.parseDouble(bean.getZaotui()), Double.parseDouble(bean.getChidao()));//早退+迟到
            double t5 = Arith.add(t3, t4);//t3+t4
            double t6 = Arith.sub(t2, t5);//总计
            bean.setZongji(t6 + "");

            gongziDao.insertBean(bean);


        }


        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("gbk");
        response.setContentType("text/html; charset=gbk");
        response
                .getWriter()
                .print(
                        "<script language=javascript>alert('操作成功');window.location.href='method!gongzilist';</script>");
    }

    //个人基本信息列表
    public String userlist2() {
        HttpServletRequest request = ServletActionContext.getRequest();

        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        sb.append("  id=" + user.getId());
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }

        request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));

        request.setAttribute("url", "method!userlist2");
        request.setAttribute("url2", "method!user");
        request.setAttribute("title", "个人基本信息查询");
        this.setUrl("user/userlist2.jsp");
        return SUCCESS;

    }

    //个人考勤信息查询列表
    public String kaoqinlist2() {
        HttpServletRequest request = ServletActionContext.getRequest();

        String truename = request.getParameter("truename");
        String leixing = request.getParameter("leixing");


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        if (truename != null && !"".equals(truename)) {

            sb.append("user.truename like '%" + truename + "%'");
            sb.append(" and ");
            request.setAttribute("truename", truename);
        }

        if (leixing != null && !"".equals(leixing)) {

            sb.append("leixing like '%" + leixing + "%'");
            sb.append(" and ");
            request.setAttribute("leixing", leixing);
        }


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        sb.append("  deletestatus=0  and user.id=" + user.getId() + " order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = kaoqinDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", kaoqinDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!kaoqinlist2", "共有" + total + "条记录"));
        request.setAttribute("url", "method!kaoqinlist2");
        request.setAttribute("url2", "method!kaoqin");
        request.setAttribute("title", "个人考勤信息查询");
        this.setUrl("kaoqin/kaoqinlist2.jsp");
        return SUCCESS;

    }

    //个人奖金查询列表
    public String jiangjinlist2() {
        HttpServletRequest request = ServletActionContext.getRequest();


        StringBuffer sb = new StringBuffer();
        sb.append(" where ");


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        sb.append("  deletestatus=0 and user.id=" + user.getId() + " order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = jiangjinDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", jiangjinDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!jiangjinlist2", "共有" + total + "条记录"));
        request.setAttribute("url", "method!jiangjinlist2");
        request.setAttribute("url2", "method!jiangjin");
        request.setAttribute("title", "个人奖金查询");
        this.setUrl("jiangjin/jiangjinlist2.jsp");
        return SUCCESS;

    }


    //个人工资查询列表
    public String gongzilist2() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String yuefen = request.getParameter("yuefen");
        StringBuffer sb = new StringBuffer();
        sb.append(" where ");
        if (yuefen != null && !"".equals(yuefen)) {
            sb.append("yuefen like '%" + yuefen + "%'");
            sb.append(" and ");
            request.setAttribute("yuefen", yuefen);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        sb.append("  user.id=" + user.getId() + " order by id desc ");
        String where = sb.toString();


        int currentpage = 1;
        int pagesize = 10;
        if (request.getParameter("pagenum") != null) {
            currentpage = Integer.parseInt(request.getParameter("pagenum"));
        }
        int total = gongziDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
        request.setAttribute("list", gongziDao.selectBeanList((currentpage - 1)
                * pagesize, pagesize, where));
        request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
                currentpage, "method!gongzilist2", "共有" + total + "条记录"));
        request.setAttribute("url", "method!gongzilist2");
        request.setAttribute("url2", "method!gongzi");
        request.setAttribute("title", "个人工资查询");
        this.setUrl("gongzi/gongzilist2.jsp");
        return SUCCESS;
    }
}