package com.gangula.yumyumapp;

public class Post_items {


    String profileName;
    String username;
    int image;

    public Post_items(String profileName, String username, int image) {
        this.profileName = profileName;
        this.username = username;
        this.image = image;
    }


    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
