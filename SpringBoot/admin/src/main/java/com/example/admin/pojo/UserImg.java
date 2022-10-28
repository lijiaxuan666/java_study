package com.example.admin.pojo;

public class UserImg {
    private String username;
    private byte[] img;
    private byte[] photos;

    public String getUsername() {
        return username;
    }

    public byte[] getImg() {
        return img;
    }

    public byte[] getPhotos() {
        return photos;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public void setPhotos(byte[] photos) {
        this.photos = photos;
    }
}
