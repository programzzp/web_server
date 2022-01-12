package com.program;

import com.program.pojo.User;
import org.Server.information_processing.objectfactory.annotation.RequestMapping;
import org.Server.information_processing.objectfactory.annotation.RequestMethod;
import org.Server.information_processing.objectfactory.annotation.RestController;

import java.util.List;

@RestController
public class Congyauds {

    @RequestMapping(path = "/getLogin",method = RequestMethod.POST)
    public User Login(List<String> list){
        User user=new User(list.get(0),list.get(1));
        return user;
    }
}
