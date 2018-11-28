package cn.yxd.servce;

import cn.yxd.bean.Subarea;
import cn.yxd.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SubAreaService {
//保存分区
    void add(Subarea modle);
//没有过滤条件的分页数据
    void pageQuery(PageBean pageBean);
//查询所有的分区数据
    List<Subarea> findAll();
//查询未关联的分区
    List<Subarea> findListAssociation();

    List<Subarea> findListByDecidedzoneId(String decidedzoneId);
}
