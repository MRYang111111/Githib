package cn.yxd.web.action;

import cn.yxd.bean.Decidedzone;
import cn.yxd.crm.Customer;
import cn.yxd.crm.CustomerService;
import cn.yxd.servce.DecidedzoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * 定区管理
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends  BaseAction<Decidedzone> {
    //运用属性驱动接受传过来的id
    private String[] subareaid;
    @Autowired
    private DecidedzoneService decidedzoneService;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    //保存定区
    public String add() throws Exception {
        /**
         * b保存和更新定区对象和分区对象，采用主键维护策略，定区的一方放弃分区的一方，（少的一方放弃多的一方）
         */
        System.out.println(modle.getStaff().getId());
        decidedzoneService.sava(modle,subareaid);
        return "toDecidedzoneList";
    }

    public String pageQuery() throws Exception {
        decidedzoneService.pageQuery(pageBean);
        this.javaToJson(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas","decidedzones","haspda","deltag"} );
        return null;
    }
    //出入crm代理对象
    @Autowired
    private CustomerService proxy;
    //获取未关联的客户对象
    public String findListNotAssociation() throws Exception {
        List<Customer> list = proxy.findListNotAssociation();
      this.listToJson(list,new String[]{} );
        return  null;
    }
    //获取已经关联的客户对象
    public String findListAssociation() throws Exception {
        String dicidedzoneid=modle.getId();
        List<Customer> list = proxy.findListAssociation(dicidedzoneid);
        this.listToJson(list,new String[]{});
        return  null;
    }
    //运用属性驱动获取customer_id
    private List<Integer> customerIds;
    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    //客户关联定区的id
    public String assigncustomerstodecidedzone() throws Exception {
        System.out.println(customerIds);
        //调用service的方法
        proxy.assigncustomerstodecidedzone(modle.getId(), customerIds);
        return null;
    }
}

