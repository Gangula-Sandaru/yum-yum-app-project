package com.gangula.yumyumapp;

public class ScreenItem {
    String Title, Descrip;
    int  screenImg;

    public ScreenItem(String title, String descrip, int screenimg){
        Title = title;
        Descrip = descrip;
        screenImg = screenimg;
    }

    // setters

    public void setTitle(String title){
        Title = title;
    }
    public void setScreenImg(String descrip){
        Descrip = descrip;
    }
    public void setScreenImg(int screenimg){
        screenImg = screenimg;
    }

    // getter
    public String getTitle(){
        return Title;
    }
    public String getDescrip(){
        return Descrip;
    }
    public int getScreenImg(){
        return screenImg;
    }
}


