package org.Server.information_processing.objectfactory.classfactory.GetController;

import org.Server.information_processing.objectfactory.classfactory.Getfilepath.GetFilePathMap;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class GetControllerClassJava {
    private List<String> list;
    private List<String> newPath=new ArrayList<String>();
    public GetControllerClassJava(){
        GetFilePathMap filePathMap=new GetFilePathMap();
        list=filePathMap.GetClassPath();
    }

    private void GetClassAnnotation(){
        for (String path : list) {
            GetRequestMapping(path);
        }
    }
    private void GetRequestMapping(String path){
        try {
            Class<?> aClass = Class.forName(path);
            Annotation[] annotations = aClass.getAnnotations();
            for (Annotation annotation : annotations) {
                newPath.add(path);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> GetClassAnnotationPath(){
        this.GetClassAnnotation();
        return newPath;
    }
}
