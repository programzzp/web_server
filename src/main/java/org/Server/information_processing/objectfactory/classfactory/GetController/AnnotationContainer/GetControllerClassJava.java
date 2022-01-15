package org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer;

import org.Server.information_processing.objectfactory.annotation.RestController;
import org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer.GetAnnotationContainer;
import org.Server.information_processing.objectfactory.classfactory.Getfilepath.GetFilePathMap;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取所有配置RestController注解的类
 */
public class GetControllerClassJava implements GetAnnotationContainer {
    private List<String> list;
    private List<String> restControllerContainer=new ArrayList<String>();

    /**
     * 当new对象时,com文件夹下所有的class文件的路径名
     */
    public GetControllerClassJava(){
        GetFilePathMap filePathMap=new GetFilePathMap();
        list=filePathMap.GetClassPath();
    }

    public void GetClassAnnotation(){
        for (String path : list) {
            GetRequestMapping(path);
        }
    }


    /**
     * 获取哪些类有注解
     * @param path
     */
    public void GetRequestMapping(String path){
        try {
            Class<?> aClass = Class.forName(path);

            /**
             * 如果为RestController就将class信息保存到newPath里面
             */
            RestController annotation2 = aClass.getAnnotation(RestController.class);
            if (annotation2!=null){
                restControllerContainer.add(path);
            }
//            System.out.println(annotation2.equals(RestController.class));
//            Annotation[] annotations = aClass.getAnnotations();
//            for (Annotation annotation : annotations) {
//                newPath.add(path);
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> GetClassAnnotationPath(){
        this.GetClassAnnotation();
        return restControllerContainer;
    }
}
