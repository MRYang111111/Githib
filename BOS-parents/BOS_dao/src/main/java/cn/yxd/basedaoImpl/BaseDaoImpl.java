package cn.yxd.basedaoImpl;

import cn.yxd.basedao.IBaseDao;
import cn.yxd.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements  IBaseDao<T> {
    //获取运行时期的实体类
    private  Class<T> clazz;
    @Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    //在父类（BaseDaoImpl）的构造方法中动态获得clazz
    public  BaseDaoImpl(){
        //获取其子类
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //申明泛型数组
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      //获取第一个数组的值
       clazz= (Class<T>) actualTypeArguments[0];
    }

    @Override
    public void save(T t) {
        this.getHibernateTemplate().save(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        //添加
        this.getHibernateTemplate().saveOrUpdate(t);
    }
    @Override
    public void delete(T t) {
        //删除
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        //更新
        this.getHibernateTemplate().update(t);
    }
    @Override
    public T findById(Serializable id) {
        //查找id
        return this.getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> getAll() {
        //书写获取额sql语句
        String hql = "FROM " + clazz.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }


    @Override
    public void executeUpdate(String queryName, Object... objects) {
        //执行更新的方法
        //获取当前的session线程对象
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        //对query进行变量赋值
       int i=0;
       for (Object object:objects){
           query.setParameter(i++, object);
       }
       //更新加载数据
        query.executeUpdate();
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        int currentPage=pageBean.getCurrentPage();
        int pageSize=pageBean.getPageSize();
        //获取离线条件
        DetachedCriteria detachedCriteria=pageBean.getDetachedCriteria();
        //设置hibernat框架的聚合函数，select count(*) from staff
        detachedCriteria.setProjection(Projections.rowCount());
        //根据hibernate模版获取数据库的记录数
        List<Long> rows = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long count = rows.get(0);
        pageBean.setTotal(count.intValue());
        //查询所需要的数据
        //清除聚合函数
        detachedCriteria.setProjection(null);
        //指定hibernate封装对象的方式，一对象的方式输出，不进行代理
        detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
        int firstResult=(currentPage-1)*pageSize;
        int maxResult=pageSize;
         List list= this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResult);
        pageBean.setRows(list);

    }

    @Override
    public List<T> findByCriterid(DetachedCriteria detachedCriteria) {
        //根据离线查询对象查询
        return (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }


}
