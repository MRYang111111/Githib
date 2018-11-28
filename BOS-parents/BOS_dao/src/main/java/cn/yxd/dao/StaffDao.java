package cn.yxd.dao;

import cn.yxd.basedao.IBaseDao;
import cn.yxd.bean.Staff;

public interface StaffDao extends IBaseDao<Staff> {
    //删除取派员
    void deleteBatch(String ids);
}
