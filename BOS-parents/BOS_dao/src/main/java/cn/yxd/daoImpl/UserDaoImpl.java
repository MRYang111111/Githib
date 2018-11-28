package cn.yxd.daoImpl;

import cn.yxd.basedao.IBaseDao;
import cn.yxd.basedaoImpl.BaseDaoImpl;
import cn.yxd.bean.User;
import cn.yxd.dao.UserDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findUserNameAndPassword(final String username, final String password) {
        return this.getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String HQL = "from User where username=? and password=?";
                Query query = session.createQuery(HQL);
                query.setParameter(0, username);
                query.setParameter(1, password);
                User user = (User) query.uniqueResult();
                if (user != null) {
                    //如果用户名和密码存在则返回一个user对像
                    System.out.println("user！=null的情况" + user);
                    return user;
                } else {
                    System.out.println("user==null的情况" + user);
                    return null;
                }
            }
        });

//    }
//   @Override
//    public User findUserNameAndPassword(final String username, final String password) {
//        return getHibernateTemplate().execute(new HibernateCallback<User>() {
//           @Override
//          public User doInHibernate(Session session) throws HibernateException {
//               //书写hql语句
////              String hql="FROM User  WHERE username=? AND password=?";
//               //依据sessionFactory创建查询对象
//               Query query = session.createQuery("from  User  where username=? and password=?");
//
//               //设置参数·
//               query.setParameter(0,username);
//              query.setParameter(1,password);
//               User user= (User) query.uniqueResult();
//               if(user!=null){
//
//               }
//              System.out.println(user);
//               return user;
//           }
//      });
//   }
        //根据用户查询模版对象

//    @Override
//    public User findUserNameAndPassword(String username, String password) {
//        String hql = " from User u where u.username = ? and u.password = ?";
//      List<User> list = (List<User>)this.getHibernateTemplate().find(hql, username, password);
//        System.out.println(list);
//      //判断是否存在该用户
//     if (list != null && list.size() > 0) {
//         return list.get(0);
//       }
//       return null;
//
//}

    }
}



