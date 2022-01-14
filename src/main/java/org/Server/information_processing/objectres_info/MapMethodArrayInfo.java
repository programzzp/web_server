package org.Server.information_processing.objectres_info;

import org.Server.pojo.MethodArray;
import org.Server.pojo.RespInformation;

import java.util.Map;

/**
 *
 */
public interface MapMethodArrayInfo {

    /**
     * 判断容器中是否有该url的信息
     * @param stringMethodMap map容器
     * @param url 请求的url
     * @return 是否容器中有信息
     */
    boolean JustStringMethodMap(Map<String, MethodArray> stringMethodMap,String url);

    /**
     * 获取容器中的响应信息
     * @param stringMethodMap map容器
     * @param url 请求的url
     * @param method 请求方式
     * @param res_data 请求数据
     * @return 该url下的响应信息
     */
    RespInformation ReturnRespInformation(Map<String, MethodArray> stringMethodMap,String url,String method,String res_data);
}
