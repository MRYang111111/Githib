package cn.yxd.serviceImpl;

import cn.yxd.bean.Subarea;
import cn.yxd.dao.SubAreaDao;
import cn.yxd.servce.SubAreaService;
import cn.yxd.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubAreaServiceImpl implements  SubAreaService {
    @Autowired
    private SubAreaDao subAreaDao;
    @Override
    public void add(Subarea subarea) {
        //保存分区
     subAreaDao.save(subarea);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        //查询没有过滤条件的分页数据
        subAreaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subAreaDao.getAll();
    }

    @Override
    public List<Subarea> findListAssociation() {
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        return subAreaDao.findByCriterid(detachedCriteria);
    }

    @Override
    public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
        //添加离线查询条件,因为，没有多表查询的存在，只存在单表所以可以使用别名
        DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
        dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
        return subAreaDao.findByCriterid(dc);
    }
}
