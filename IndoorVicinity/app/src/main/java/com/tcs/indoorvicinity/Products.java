package com.tcs.indoorvicinity;

public class Products {
    private String product_name;
    private String product_price;
    private String product_discount;

    public Products(String product_name, String product_price, String product_discount) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_discount = product_discount;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_discount() {
        return product_discount;
    }

    public void setProduct_discount(String product_discount) {
        this.product_discount = product_discount;
    }
}
