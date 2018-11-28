package cn.yxd.utils;


import cn.yxd.bean.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/*
Bos项的工具类
 */
public class BosUtils {
    //获取session
    public  static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }
    //用户的登入对象
    public  static User getLoginUser(){
        return (User) getSession().getAttribute("user");
    }


}

