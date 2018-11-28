package cn.yxd.dao;

import cn.yxd.basedao.IBaseDao;
import cn.yxd.bean.Region;

import java.util.List;

public interface RegionDao  extends IBaseDao<Region> {
    List<Region> findRegionByQ(String q);
}
