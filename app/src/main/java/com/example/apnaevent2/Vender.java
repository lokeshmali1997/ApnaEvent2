package com.example.apnaevent2;

public class Vender {
    private String name;
    private long mob;
    private String email;
    private  String add;
    private String img;
    private int category;
    private int rating;

    public Vender() {
    }

    public Vender(String name, long mob, String email, String add, String img, int category, int rating) {
        this.name = name;
        this.mob = mob;
        this.email = email;
        this.add = add;
        this.img = img;
        this.category = category;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMob() {
        return mob;
    }

    public void setMob(long mob) {
        this.mob = mob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
