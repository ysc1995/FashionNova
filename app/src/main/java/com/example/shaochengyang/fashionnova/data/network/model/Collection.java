package com.example.shaochengyang.fashionnova.data.network.model;

public class Collection {
    String cid, cname, cdiscription, cimagerl;

    public Collection(String cid, String cname, String cdiscription, String cimagerl) {
        this.cid = cid;
        this.cname = cname;
        this.cdiscription = cdiscription;
        this.cimagerl = cimagerl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdiscription() {
        return cdiscription;
    }

    public void setCdiscription(String cdiscription) {
        this.cdiscription = cdiscription;
    }

    public String getCimagerl() {
        return cimagerl;
    }

    public void setCimagerl(String cimagerl) {
        this.cimagerl = cimagerl;
    }
}
