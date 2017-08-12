package com.example.shariqkhan.chatnsnap;

/**
 * Created by ShariqKhan on 8/11/2017.
 */

public class RequestModel {

    String id;
    String reqType;

    public RequestModel(String id, String reqType) {
        this.id = id;
        this.reqType = reqType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }
}
