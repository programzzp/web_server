package org.Server.utli;

import org.Server.pojo.MethodArray;

import java.util.ArrayList;
import java.util.List;

public class ParameterJudge {
    private MethodArray methodArray;

    private Class<?>[] parameterTypes;

    public void init(MethodArray methodArray){
        this.methodArray=methodArray;
    }

    public boolean parameter_Judge(){
        parameterTypes = methodArray.getJavaMethod().getParameterTypes();
        if (parameterTypes.length==0){
            return false;
        }else{
            return true;
        }
    }

    public List<String> getParameter(String data){
        List<String> list=new ArrayList<String>();
        String[] res_data = data.split("&");
        for (String res_datum : res_data) {
            String[] split = res_datum.split("=");
            list.add(split[1]);
        }
        return list;
    }


}
