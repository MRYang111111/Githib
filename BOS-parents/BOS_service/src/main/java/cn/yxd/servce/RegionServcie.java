package cn.yxd.servce;

import cn.yxd.bean.Region;
import cn.yxd.utils.PageBean;

import java.util.List;

public interface RegionServcie {
    //文件上传
    void inputFile(List<Region> list);
    //分页
    void pageQugery(PageBean pageBean);
    //添加
    void add(Region region);
    //ajax回显，查询所有的区域
    List<Region> findAll();
    //对区域进行迷糊查询
    List<Region> findRegionByQ(String q);
}
