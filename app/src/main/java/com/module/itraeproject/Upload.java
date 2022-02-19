package com.module.itraeproject;

public class Upload {
    private String mImageUrl;

    public Upload(){}

    public Upload(String ImageUrl)
    {
        this.mImageUrl=ImageUrl;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public void setmImageUrl(String ImageUrl){
        this.mImageUrl=ImageUrl;
    }
}
