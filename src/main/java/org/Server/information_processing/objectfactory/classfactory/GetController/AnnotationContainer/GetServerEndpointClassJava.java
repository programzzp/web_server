package org.Server.information_processing.objectfactory.classfactory.GetController.AnnotationContainer;

import org.Server.information_processing.objectfactory.annotation.RestController;
import org.Server.information_processing.objectfactory.annotation.WebSocketAnnotation.ServerEndpoint;
import org.Server.information_processing.objectfactory.classfactory.Getfilepath.GetFilePathMap;

import java.util.ArrayList;
import java.util.List;

public class GetServerEndpointClassJava implements GetAnnotationContainer{

    private List<String> list;
    private List<String> restControllerContainer=new ArrayList<String>();

    /**
     * 当new对象时,com文件夹下所有的class文件的路径名
     */
    public GetServerEndpointClassJava(){
        GetFilePathMap filePathMap=new GetFilePathMap();
        list=filePathMap.GetClassPath();
    }


    @Override
    public void GetClassAnnotation() {
        for (String path : list) {
            GetRequestMapping(path);
        }
    }

    @Override
    public void GetRequestMapping(String path) {
        try {
            Class<?> aClass = Class.forName(path);

            /**
             * 如果为RestController就将class信息保存到newPath里面
             */
            ServerEndpoint annotation2 = aClass.getAnnotation(ServerEndpoint.class);
            if (annotation2!=null){
                restControllerContainer.add(path);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<String> GetClassAnnotationPath(){
        this.GetClassAnnotation();
        return restControllerContainer;
    }
}
