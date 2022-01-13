package org.Server.pojo;

public class RespInformation {
    private String body;
    private int Code;
    private String type;

    public RespInformation(){}

    public RespInformation(String body, int code, String type) {
        this.body = body;
        Code = code;
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RespInformation{" +
                "body='" + body + '\'' +
                ", Code=" + Code +
                ", type='" + type + '\'' +
                '}';
    }
}
