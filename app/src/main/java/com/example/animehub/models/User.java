package com.example.animehub.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {
    public static final String KEY_IMAGE = "image";
    public static final String KEY_BIO = "bio";
    public static final String KEY_USERNAME = "username";

    public String getUsername() {return getString(KEY_USERNAME); }

    public void setBio(String bio) {put(KEY_BIO,bio);}
    public String getBio() {return getString(KEY_BIO); }

    public ParseFile getImage() { return getParseFile(KEY_IMAGE); }

    public void setImage(ParseFile parseFile) { put(KEY_IMAGE, parseFile); }




}
