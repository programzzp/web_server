package org.Server.information_processing.objectres_info;

import com.alibaba.fastjson.JSON;
import org.Server.pojo.MethodArray;
import org.Server.pojo.RespInformation;
import org.Server.utli.ParameterJudge;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;


/**
 *
 */
public class MapMethodArrayInfoImpl implements MapMethodArrayInfo{


    /**
     *
     * @param stringMethodMap map容器
     * @param url 请求的url
     * @return
     */
    public boolean JustStringMethodMap(Map<String, MethodArray> stringMethodMap, String url) {
        /**
         * 判断容器中没有数据获取没有指定的url
         */
        if (stringMethodMap.keySet().size()==0||stringMethodMap.get(url)==null){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @param stringMethodMap map容器
     * @param url 请求的url
     * @param method 请求方式
     * @param res_data 请求数据
     * @return
     */
    public RespInformation ReturnRespInformation(Map<String, MethodArray> stringMethodMap, String url, String method,String res_data) {

        if (stringMethodMap.get(url).getRequestMethod().equals(method)){
            try {
                /**
                 *
                 */
                MethodArray methodArray = stringMethodMap.get(url);
                /**
                 * 请全体的处理
                 */
                ParameterJudge judge=new ParameterJudge();
                judge.init(methodArray);
                boolean b = judge.parameter_Judge();
                Object invoke=null;
                if (b&&res_data!=null){

                    List<String> parameter = judge.getParameter(res_data);
                    invoke = methodArray.getJavaMethod().invoke(methodArray.getJavaClass(),parameter);

                }else{
                    try {
                        invoke = methodArray.getJavaMethod().invoke(methodArray.getJavaClass());
                    }catch (Exception e){
                        invoke=e.toString();
                        System.out.println(e);
                    }
                }
                String json = JSON.toJSONString(invoke);
                return new RespInformation(json,200,"json");
            } catch (IllegalAccessException e) {
                return new RespInformation(e.toString(),400,"json");
            } catch (InvocationTargetException e) {
                return new RespInformation(e.toString(),400,"json");
            }
        }
        return new RespInformation("error",400,"json");
    }


}
