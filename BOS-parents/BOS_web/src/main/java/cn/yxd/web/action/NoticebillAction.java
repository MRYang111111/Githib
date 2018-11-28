package cn.yxd.web.action;

import cn.yxd.bean.Noticebill;
import cn.yxd.crm.Customer;
import cn.yxd.crm.CustomerService;
import cn.yxd.servce.NoticeBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class NoticebillAction extends  BaseAction<Noticebill> {
    /**
     *
     * y业务通知单的action
     * @return
     * @throws Exception
     */
    @Autowired
    private CustomerService customerService;
    @Autowired
    private NoticeBillService noticeBillService;
    //根据业务通知单查询action
    public String findListByTelephone() throws Exception {
        Customer customer = customerService.findListByTelephone(this.modle.getTelephone());
        javaToJson(customer,new String[]{} );
        return  null;
    }
//    //添加新单
//    public String addnoticebill() throws Exception {
//        //调用service的保存方法
//        noticeBillService.save(this.modle);
//        return "add";
//    }

    public String addnoticebill() {
        noticeBillService.save(this.modle);

      return  "add";
    }
}
