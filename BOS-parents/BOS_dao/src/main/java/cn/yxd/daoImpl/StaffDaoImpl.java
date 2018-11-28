package cn.yxd.daoImpl;

import cn.yxd.basedaoImpl.BaseDaoImpl;
import cn.yxd.bean.Staff;
import cn.yxd.dao.StaffDao;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    @Override
    public void deleteBatch(String ids) {
        this.executeUpdate("staff.delete",ids);
    }
}
