package com.example.apnaevent2;

public class Product {
    private String pId;
    private String pName;
    private String pPrice;

    public Product(String pId, String pName, String pPrice, String pPer) {
        this.pId = pId;
        this.pName = pName;
        this.pPrice = pPrice;
        this.pPer = pPer;
    }

    private String pPer;

    public Product() {
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpPer() {
        return pPer;
    }

    public void setpPer(String pPer) {
        this.pPer = pPer;
    }
}
