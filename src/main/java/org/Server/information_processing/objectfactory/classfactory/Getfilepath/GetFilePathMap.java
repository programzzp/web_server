package org.Server.information_processing.objectfactory.classfactory.Getfilepath;

import org.Server.information_processing.objectfactory.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取com路径下所有的类的class 路径
 */
public class GetFilePathMap {
    private List<String> path=new ArrayList<String>();

    /**
     * 获取路径
     */
    private final String file_path="src/main/java/com/";


    private void pathFile(){
        File file=new File(file_path);
        GetJavaFiles(file);
    }

    /**
     * 遍历所有路径，将class路径保存到List集合中
     * @param file
     */
    private void GetJavaFiles(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                GetJavaFiles(f);
            if(f.isFile()){
                String oldPath=f.toString();
                String old = oldPath.replace('\\', '.');
                String ClassPath = old.replace("src.main.java.", "").replace(".java","");
                path.add(ClassPath);
            }
        }
    }

    /**
     * 返回List集合
     * @return
     */
    public List<String> GetClassPath(){
        pathFile();
        return path;
    }


}
