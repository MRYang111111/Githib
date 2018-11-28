package cn.yxd.web.action;

import cn.yxd.bean.User;
import cn.yxd.crm.Customer;
import cn.yxd.crm.CustomerService;
import cn.yxd.servce.UserService;

import cn.yxd.utils.BosUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
    String f = "1";
    //注入依赖'
    /**注入失败
     *
     */
//    @Resource(name = "CustomerService")
//    private CustomerService customerService;

    public static final String HOME = "home";
    //采用模型驱动的方式获取session中的验证码
    private  String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    public String getCheckcode() {
        return checkcode;
    }
    @Autowired
    private UserService service;
        //登入
//    public String test() throws Exception {
//        List<Customer> list = customerService.getAll();
//        for (Customer customer:list){
//            System.out.println(customer);
//        }
//        return "test";
//    }

    public String login() throws Exception {
        //登入时判断验证码是否正确
        String  key = (String) ActionContext.getContext().getSession().get("key");
        //判断是否验证码输入正确
        if(org.apache.commons.lang3.StringUtils.isNoneBlank(checkcode) && checkcode.equals(key)){
            //正确，则，进行下一步操作，跳转到首页
            System.out.println("输入的验证码为"+checkcode+" "+"实际的验证码为"+" "+key);
            //输入的验证码正确
            User u=service.login(modle);
            System.out.println(u);
//                if(u==null){
//                    //数据登入失败
//                    // 转发到异常信息
//                    this.addActionError("用户名或密码错误");
//                    return LOGIN;
//                }else {
//                //保存到session域中
//                ActionContext.getContext().getSession().put("user",u);
//                return "home";
//            }
            if(u!=null){
                System.out.println(u);
                //登录成功,将user对象放入session，跳转到首页
                ActionContext.getContext().getSession().put("user",u);
                return "home";
            }else{
                //转发异常信息，声明用户或密码错误
                this.addActionError("用户名或密码错误");
                return  "login";
            }
        }else {
            //不正确，输出异常信息，从新返回登入页面
            this.addActionError("验证码错误");
                return LOGIN;
        }
    }

    public void setService(UserService service) {
        this.service = service;
    }

        //注销登入的方法
    public String loginout() throws Exception {
        //销毁session
        ServletActionContext.getRequest().getSession().invalidate();
        return "login";
    }
//修改密码的方法
    public String editPassword() throws Exception {
        //获取sessio中的user
        User user = BosUtils.getLoginUser();
        System.out.println(user.getId());
        //获取用户的id//调用service的方法
        service.editPassword(user.getPassword(),user.getId());
        return null;
    }
}
