package cn.yxd.serviceImpl;

import cn.yxd.bean.Staff;
import cn.yxd.dao.StaffDao;
import cn.yxd.servce.StaffService;
import cn.yxd.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Override
    public void save(Staff staff) {
        //调用dao的方法
        String f="0";
        try {
            staffDao.save(staff);
            f="1";
        }catch (Exception e){
            f="0";
            e.printStackTrace();
        }
        System.out.println(f);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        //直接调用dao
        staffDao.pageQuery(pageBean);

    }

    @Override
    public void deleteBatch(String ids) {
        //判断id是否为空，并且分割字符串
        if(StringUtils.isNoneBlank(ids)){
            String[] split = ids.split(",");
            //直接调用dao
            //遍历
            for (String string:split){
                staffDao.deleteBatch(string);
            }
        }

    }

    @Override
    public void update( Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public List<Staff> findByNotDelete() {
        //查询未删除的取派员信息
      DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Staff.class);
      //添加过滤条件,查询deltag=0的取派员数据
       detachedCriteria.add(Restrictions.eq("deltag", "0"));
        List<Staff> list = staffDao.findByCriterid(detachedCriteria);
        return list;
    }


}
