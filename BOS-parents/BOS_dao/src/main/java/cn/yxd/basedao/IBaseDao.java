package cn.yxd.basedao;

import cn.yxd.bean.Staff;
import cn.yxd.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
    //持久层代码的抽取
    //添加
    void save(T t);
    void saveOrUpdate(T t);
    //删除
     void delete(T t);
    //更新
    void update(T t);
    //根据id查询
    T findById(Serializable id);
    //查询分页
    List<T> getAll();
    //通用执行更新的方法
    void  executeUpdate(String queryName,Object...objects);
    //分页的工具丰富
    void pageQuery(PageBean pageBean);
    //创建所有的离线查询对象
    List<T> findByCriterid(DetachedCriteria detachedCriteria);




}
