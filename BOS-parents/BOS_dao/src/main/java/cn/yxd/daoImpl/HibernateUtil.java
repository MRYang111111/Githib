package cn.yxd.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    /***
     * 当多线程从新访问配置文件的时候，需要多次加载配置文件，
     * 造成线程不够安全，所以采用静态代码块
     *
     * @return
     *
     */

     public  static SessionFactory sf;

    static {
        //创建配置文件的加载对象
        Configuration configuration=new Configuration().configure();
        //创建session对象
        sf=configuration.buildSessionFactory();
    }
    //获得当前的session
    public static Session getOpenSession(){
        Session session = sf.openSession();
        return  session;
    }
    //获取绑定线程的session
    public  static Session getCurrentSession(){
        //获取与线程绑定的session
        Session currentSession = sf.getCurrentSession();
        return  currentSession;
    }

}
