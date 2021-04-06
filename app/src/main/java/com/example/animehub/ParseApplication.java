package com.example.animehub;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DglS3sXbz7x4LsmO83aSa411Y1Kf8sMGkMd2f8GX")
                .clientKey("GDVkKHSFfCQMSS5g5JCUhQZ6oXvsQxkoqEd7ZLmK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
