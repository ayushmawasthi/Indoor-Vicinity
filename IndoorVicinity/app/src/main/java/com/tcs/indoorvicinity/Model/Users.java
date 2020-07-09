package com.tcs.indoorvicinity.Model;

public class Users {
    String name,phone,password,shopname,wmail;
    public Users()
    {

    }

    public Users(String name, String phone, String password, String shopname, String wmail) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.shopname = shopname;
        this.wmail = wmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getWmail() {
        return wmail;
    }

    public void setWmail(String wmail) {
        this.wmail = wmail;
    }
}
