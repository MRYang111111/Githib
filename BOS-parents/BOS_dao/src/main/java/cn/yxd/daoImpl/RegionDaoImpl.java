package cn.yxd.daoImpl;

import cn.yxd.basedaoImpl.BaseDaoImpl;
import cn.yxd.bean.Region;
import cn.yxd.dao.RegionDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements  RegionDao {
    @Override
    public List<Region> findRegionByQ(String q) {
        String hql="from Region r where r.shortcode like ? or r.citycode like ? or r.province like ? or r.city like ?";
        List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql, "%" + q + "%", "%" + q + "%", "%" + q + "%", "%" + q + "%");
        return list;
    }
}
