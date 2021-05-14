package com.example.animehub.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.sql.Date;

@ParseClassName("Post")
public class Post extends ParseObject{
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "userId";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_RESPONSE = "spoiler";

    public String getDescription(){ return getString(KEY_DESCRIPTION); }

    public void setDescription(String description) { put(KEY_DESCRIPTION, description); }

    public ParseFile getImage() { return getParseFile(KEY_IMAGE); }

    public void setImage(ParseFile parseFile) { put(KEY_IMAGE, parseFile); }

    public ParseUser getUser() { return getParseUser(KEY_USER); }

    public void setUser(ParseUser username) { put(KEY_USER, username); }

    public String getTitle(){ return getString(KEY_TITLE); }

    public void setTitle(String title) { put(KEY_TITLE, title); }

    public String getSpoiler(){ return getString(KEY_RESPONSE); }

    public void setSpoiler(String spoiler) { put(KEY_RESPONSE, spoiler); }

}


