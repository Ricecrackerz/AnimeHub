package com.example.animehub.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_BIO = "bio";

    public String getBio(){
        return getString(KEY_BIO); }

    public void setBio(String bio) {
        put(KEY_BIO, bio); }
}
