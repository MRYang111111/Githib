package cn.yxd.dao;

import cn.yxd.basedao.IBaseDao;
import cn.yxd.bean.User;

public interface UserDao extends IBaseDao<User> {

    User findUserNameAndPassword(String username, String password);
}
