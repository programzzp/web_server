package org.Server.pojo;

/**
 * 响应类型的数据结构
 */
public class Head {
    private String key;
    private String value;


    public Head(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Head{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
