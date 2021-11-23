package com.example.a485groupproject;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Application;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Profile.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("DASsKTzEyXcAlkWj69x8r08rnXprf46UHZr7PHgg")
                .clientKey("yarqeeYIIZIeR7zqhS3SEjdo83RjHChcr1dcwN8F")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
