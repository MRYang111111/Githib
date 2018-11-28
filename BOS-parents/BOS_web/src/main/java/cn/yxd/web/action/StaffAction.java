package cn.yxd.web.action;

import cn.yxd.bean.Staff;
import cn.yxd.servce.StaffService;
import cn.yxd.utils.PageBean;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class StaffAction extends  BaseAction<Staff> {

    //添加快递员
    @Autowired
    private StaffService staffService;
    //使用模型驱动获取当前的currentPage和pageSize和DetachedCriteria
    private int page;
    private  int rows;
    //引用属性驱动，设置客户传过来的id
    private  String ids;


    //添加取派员
    public String add() throws Exception {
        //取代员得添加
        //调用service的方法
        staffService.save(modle);
        return "staffList";
    }
    //查询分页的方法
    public String pageQuery() throws Exception {
     //创建pageBean对象
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建连线查询对象
        DetachedCriteria detachedCriteria= DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);

        //调用service的方法
        staffService.pageQuery(pageBean);
        //将数据转换成json格式
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize","decidedzones"});
        String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
        //设置编码格式
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        //将字符串转换成json格式输出
        ServletActionContext.getResponse().getWriter().print(json);
        return null;
    }
    //删除用户，其实是更新用户的操作
    public String deleteBatch() throws Exception {
        //直接调用service
        staffService.deleteBatch(ids);
        return "list";
    }
    //修改用户
    public String edit() throws Exception {
        //根据id查询数据库
        System.out.println("客户的id是"+modle.getId());
        Staff staff=staffService.findById(modle.getId());
        //对原因的数据进行覆盖
        staff.setName(modle.getName());
        staff.setTelephone(modle.getTelephone());
        staff.setHaspda(modle.getHaspda());
        staff.setStandard(modle.getStandard());
        staff.setStation(modle.getStation());
        staffService.update(staff );
        return "edit";
    }
//    加载未删除的1取派员信息
    public String listajax() throws Exception {
        java.util.List<Staff> staffList=staffService.findByNotDelete();
//        java.util.List<Staff> byNotDelete = staffService.findByNotDelete();
        listToJson(staffList, new String[]{"decidedzones"});
        return null;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public void setStaffService(StaffService staffService) {
        this.staffService = staffService;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
