package hmst.spring.service.impl;

import hmst.spring.HelloService;

/**
 * Created by Administrator on 2018/3/5 0005.
 */
public class HelloServiceImpl  implements HelloService{
    public String hello(String name){
        return "hello!"+name;
    }

    public void test(){
        System.out.println("test");
    }
}
