package cn.yxd.servce;

import cn.yxd.bean.User;

import java.io.IOException;

public interface UserService {
    //登入的方法
    public  User login(User modle);
    //修改密码
    void editPassword(String password, String id) throws IOException;

}
