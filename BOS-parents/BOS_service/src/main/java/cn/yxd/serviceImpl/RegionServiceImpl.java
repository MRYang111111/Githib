package cn.yxd.serviceImpl;

import cn.yxd.bean.Region;
import cn.yxd.dao.RegionDao;
import cn.yxd.servce.RegionServcie;
import cn.yxd.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegionServiceImpl implements RegionServcie {
    @Autowired
    private RegionDao regionDao;
    @Override
    public void inputFile(List<Region> list) {
        //遍历后直接调用进行文件上传
        for (Region r:list){
            regionDao.saveOrUpdate(r);
        }
    }
//分页
    @Override
    public void pageQugery(PageBean pageBean) {
        //直接调用dao
        regionDao.pageQuery(pageBean);
    }

    @Override
    public void add(Region region) {
        //添加
        regionDao.save(region);
    }

    @Override
    public List<Region> findAll() {
        //查询所有的区域数据
        return regionDao.getAll();
    }

    @Override
    public List<Region> findRegionByQ(String q) {
        //对区域进行迷糊查询
        return regionDao.findRegionByQ(q);
    }
}
