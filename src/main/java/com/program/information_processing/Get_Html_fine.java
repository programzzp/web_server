package com.program.information_processing;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取html文件保存到Map集合中
 * 所有html文件在src\main\resources\web\下
 */
public class Get_Html_fine {
    private static final String FILE="src/main/resources/web";

    private static HashMap<String,String> HTML_FILE=new HashMap<String,String>();

    static {
        File file=new File(FILE);
        File[] files = file.listFiles();
        for (File file1 : files) {
            String html_file = file1.toString().replace("src\\main\\resources\\web\\", "");
            HTML_FILE.put(html_file,file1.toString());
        }
    }

    public static HashMap<String,String> Get_HTML_FILE(){
        return HTML_FILE;
    }

}
