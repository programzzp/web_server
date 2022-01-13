package org.Server.information_processing.objectres_info;

import org.Server.pojo.MethodArray;
import org.Server.pojo.RespInformation;

import java.util.Map;

public interface MapMethodArrayInfo {
    boolean JustStringMethodMap(Map<String, MethodArray> stringMethodMap,String url);

    RespInformation ReturnRespInformation(Map<String, MethodArray> stringMethodMap,String url,String method,String res_data);
}
