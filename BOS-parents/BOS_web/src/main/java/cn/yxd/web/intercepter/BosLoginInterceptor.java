package cn.yxd.web.intercepter;

import cn.yxd.bean.User;
import cn.yxd.utils.BosUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class BosLoginInterceptor extends MethodFilterInterceptor {
    //登入校验的拦截器
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //打印url
        ActionProxy actionProxy=actionInvocation.getProxy();
        String actionname =  actionProxy.getActionName();
        String namespace=actionProxy.getNamespace();
        System.out.println("调用action的名称是"+actionname);
        System.out.println("调用action的namespace是"+namespace);
        String url=actionname+namespace;
        System.out.println("url"+url);
        //session中回去用户是否已经登入获取用户的对象
        User loginUser = BosUtils.getLoginUser();
        if(loginUser!=null){
            actionInvocation.invoke();
        }
            //返回登入界面
        return "login";

    }
}
