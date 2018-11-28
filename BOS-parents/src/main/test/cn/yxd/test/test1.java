package cn.yxd.test;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
@WebService
public class test1 {
    public  String sayHello(String name,int age){
        System.out.println(name);
        return  "sayHello"+name+" "+age;
    }

    public static void main(String[] args) {
        String addrsss="http://192.168.1.107:8080/hello";
        Object implementor=new test1();
//        ((test1) implementor).sayHello("zds", 12);
        Endpoint.publish(addrsss,implementor );



    }

}
