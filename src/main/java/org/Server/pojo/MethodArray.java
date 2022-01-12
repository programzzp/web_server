package org.Server.pojo;

import java.lang.reflect.Method;

public class MethodArray {
    private Object JavaClass;
    private Method JavaMethod;
    private String MethodName;
    private String RequestMethod;
    public MethodArray(){}

    public MethodArray(Object javaClass, Method javaMethod, String methodName, String requestMethod) {
        JavaClass = javaClass;
        JavaMethod = javaMethod;
        MethodName = methodName;
        RequestMethod = requestMethod;
    }

    public Object getJavaClass() {
        return JavaClass;
    }

    public void setJavaClass(Object javaClass) {
        JavaClass = javaClass;
    }

    public Method getJavaMethod() {
        return JavaMethod;
    }

    public void setJavaMethod(Method javaMethod) {
        JavaMethod = javaMethod;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public String getRequestMethod() {
        return RequestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        RequestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "MethodArray{" +
                "JavaClass=" + JavaClass +
                ", JavaMethod=" + JavaMethod +
                ", MethodName='" + MethodName + '\'' +
                ", RequestMethod='" + RequestMethod + '\'' +
                '}';
    }
}
