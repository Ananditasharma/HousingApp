package com.example.housingapp.Activity;

class MyListData {
    private String description;
    private int imgId;
    private String email;
    private String number;
    private String name;
    public MyListData(String description, int imgId,String email,String number,String name) {
        this.description = description;
        this.imgId = imgId;
        this.email = email;
        this.number = number;
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
