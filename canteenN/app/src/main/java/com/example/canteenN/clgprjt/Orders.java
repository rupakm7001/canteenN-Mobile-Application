package com.example.canteenN.clgprjt;

public class Orders extends OrderPostId{

    public String id,desc,email,price,quantity,classNumber;

    public Orders()
    {

    }

    public Orders(String id,String desc, String email, String price, String quantity, String classNumber) {

        this.id =id;
        this.desc = desc;
        this.email = email;
        this.price = price;
        this.quantity = quantity;
        this.classNumber =classNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getClassNumber(){
        return classNumber;
    }

    public void setClassNumber(){
        this.classNumber = classNumber;
    }
}
