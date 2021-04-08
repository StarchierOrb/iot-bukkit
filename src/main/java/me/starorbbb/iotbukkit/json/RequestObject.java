package me.starorbbb.iotbukkit.json;

public class RequestObject {
    private String target;
    private String msg;

    public RequestObject(String target, String msg) {
        this.target = target;
        this.msg = msg;
    }

    public String getTarget() {
        return target;
    }
    public String getMsg() {
        return msg;
    }
}
