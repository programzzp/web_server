package com.program;

import com.program.pojo.User;
import org.Server.information_processing.objectfactory.annotation.RequestMapping;
import org.Server.information_processing.objectfactory.annotation.RequestMethod;
import org.Server.information_processing.objectfactory.annotation.RestController;

/**
 * 加入http容器
 * 用spring使用相似
 */
@RestController
public class Controller {

    /**
     * path 请求路径
     * method 请求方式
     * @return pojo类
     */
    @RequestMapping(path = "/path01",method = RequestMethod.GET)
    public User test(){
        System.out.println("json");
        User user=new User("123","123123");
        return user;
    }


    @RequestMapping(path = "/path02",method = RequestMethod.GET)
    public User test1(){
        User user=new User("fwqdv fvdfcf","qw ecwwfeqwdfw");
        return user;
    }

    @RequestMapping(path = "/path03",method = RequestMethod.GET)
    public void test2(){
        System.out.println("json");
    }
}
