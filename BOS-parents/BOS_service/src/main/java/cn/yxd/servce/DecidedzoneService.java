package cn.yxd.servce;

import cn.yxd.bean.Decidedzone;
import cn.yxd.utils.PageBean;

public interface DecidedzoneService {
    //保存定区对象
    void sava(Decidedzone modle, String[] subareaid);
//分页查询
    void pageQuery(PageBean pageBean);
}
