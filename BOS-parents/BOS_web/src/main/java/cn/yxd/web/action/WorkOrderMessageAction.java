package cn.yxd.web.action;

import cn.yxd.bean.Workordermanage;
import cn.yxd.servce.WorkOrderMessageService;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class WorkOrderMessageAction extends  BaseAction<Workordermanage>{
    String f="f";
    @Autowired
    private WorkOrderMessageService workOrderMessageService;
    //添加工作单
    public String add() throws Exception {
        try {
            f="t";
            workOrderMessageService.save(modle);
        }catch (Exception e){
            f="f";
            throw  new RuntimeException(e);
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return null;
    }
}
