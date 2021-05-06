package com.example.animehub;

import android.app.Application;

import com.example.animehub.models.Comment;
import com.example.animehub.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Comment.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DglS3sXbz7x4LsmO83aSa411Y1Kf8sMGkMd2f8GX")
                .clientKey("GDVkKHSFfCQMSS5g5JCUhQZ6oXvsQxkoqEd7ZLmK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
