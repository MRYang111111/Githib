package cn.yxd.serviceImpl;

import cn.yxd.bean.Decidedzone;
import cn.yxd.bean.Subarea;
import cn.yxd.dao.DecidedzoneDao;
import cn.yxd.dao.SubAreaDao;
import cn.yxd.servce.DecidedzoneService;
import cn.yxd.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private SubAreaDao subAreaDao;
    @Override
    public void sava(Decidedzone modle, String[] subareaid) {
        //保存定区
        decidedzoneDao.save(modle);
        //主键维护外键，通常是少的一方放弃，多的一方维护(即采用分区关联定区)
        for ( String id:subareaid){
            Subarea subarea = subAreaDao.findById(id);
            //更行语句
            subarea.setDecidedzone(modle);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }


}
