package cn.yxd.web.action;

import cn.yxd.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    /**
     *
     *  //选择重构分页代码
     */
    //创建pageBean对象
    PageBean pageBean=new PageBean();
    protected   int page;
    protected   int  rows;
    protected  DetachedCriteria detachedCriteria=null;
    public void setPage(int page) {
     pageBean.setCurrentPage(page);
    }
    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    public static final String Home ="Home" ;
    //表现层类型的收取
    public   T  modle;
    //实例化运行父类
    public  BaseAction()  {

        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取泛型的数组
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        Class<T> entryClass= (Class<T>) actualTypeArguments[0];
        detachedCriteria=DetachedCriteria.forClass(entryClass);
        pageBean.setDetachedCriteria(detachedCriteria);
        //创建反射类型的对象
        try {
            modle= entryClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public T getModel() {
        //获取运行时期的父类
        return modle;
    }
    //将java对象转换成json的ff
    protected  void  javaToJson(Object o,String[] arrays){
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(arrays);
        String json = JSONObject.fromObject(o, jsonConfig).toString();
        //设置编码格式
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        //将字符串转换成json格式输出
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //将list集合转换成json
    protected  void  listToJson(List l, String[] arrays){
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(arrays);
        String json = JSONArray.fromObject(l, jsonConfig).toString();
        //设置编码格式
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        //将字符串转换成json格式输出
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
