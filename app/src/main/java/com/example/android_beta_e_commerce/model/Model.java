package com.example.android_beta_e_commerce.model;

public class Model {
    String productImg;
    String productName;
    String productPrice;
    String category;

    public Model(String productImg,String productName,String productPrice,String category){
        this.productImg = productImg;
        this.productName=productName;
        this.productPrice=productPrice;
        this.category=category;
    }

    public String getProductImg() {
        return productImg;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setImg(String productImg) {
        this.productImg = productImg;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
