package com.example.animehub.models;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

@ParseClassName("Comment")
public class Comment extends ParseObject {
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_POST = "postID";
    public static final String KEY_USER = "username";
    public static final String KEY_TITLE = "title";
    public static final String KEY_CREATED_KEY = "createdAt";

    public String getDescription(){
        return getString(KEY_DESCRIPTION); }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description); }

    public ParseObject getPost() {
        return getParseObject(KEY_POST); }

    public void setPost(ParseObject currentPost) {
        put(KEY_POST, currentPost);
    }

    /*public ParseUser setPost(ParseUser currentPost){
        ParseObject object = new ParseObject("Comment");
        // do whatever you want with your object here
        object.put("postId", KEY_POST);
        // save object so an ID is created
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                // Now you can do whatever you want with the object ID, like save it in a variable
                String objectId = object.getObjectId();
            }
        });

        return getParseObject(KEY_POST);
        put(KEY_POST, currentPost);

     }*/

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser username) {
        put(KEY_USER, username); }

    public String getTitle(){ return getString(KEY_TITLE); }

    public void setTitle(String title) { put(KEY_TITLE, title); }
}
