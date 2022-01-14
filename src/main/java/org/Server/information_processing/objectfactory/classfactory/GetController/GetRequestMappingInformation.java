package org.Server.information_processing.objectfactory.classfactory.GetController;

import org.Server.information_processing.objectfactory.annotation.RequestMapping;
import org.Server.pojo.MethodArray;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取所有
 */
public class GetRequestMappingInformation {
    private List<String> list;
    private Map<String,MethodArray> methodString=new HashMap<String, MethodArray>();
    public GetRequestMappingInformation(){
        GetControllerClassJava getControllerClassJava=new GetControllerClassJava();
        list = getControllerClassJava.GetClassAnnotationPath();
    }

    private void GetClassMethod(){
        for (String path : list) {
            RestControllerMethod(path);
        }
    }

    private void RestControllerMethod(String path){
        try {
            Class<?> aClass = Class.forName(path);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    RequestMapping annotation1 = method.getAnnotation(RequestMapping.class);
                    MethodArray methodArray=new MethodArray();
                    methodArray.setJavaClass(aClass.newInstance());
                    methodArray.setJavaMethod(method);
                    methodArray.setMethodName(method.getName());
                    methodArray.setRequestMethod(annotation1.method().toString());
                    methodString.put(annotation1.path(),methodArray);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Map<String,MethodArray> GetMethodString(){
        this.GetClassMethod();
        return methodString;
    }
}
