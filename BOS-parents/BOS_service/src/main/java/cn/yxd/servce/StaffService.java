package cn.yxd.servce;

import cn.yxd.bean.Staff;
import cn.yxd.utils.PageBean;

import java.util.List;

public interface StaffService {
    //添加取派员
    void save(Staff modle);
    //实现分页数据
    void pageQuery(PageBean pageBean);
    //删除取派员
    void deleteBatch(String ids);
//保存修改的用户
    void update(Staff staff);
//根据id进行查询数据库
    Staff findById(String id);
//查询未删除的取派员信息
    List<Staff> findByNotDelete();
}
