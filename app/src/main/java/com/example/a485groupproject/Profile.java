package com.example.a485groupproject;

import android.view.View;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;

@ParseClassName("Profile")
public class Profile extends ParseObject {

    public Profile() {
        super();
    }

    public Profile(ParseUser user, String username, String biography, String name, String college, ParseFile image) {
        super();
        setUsername(username);
        setBiography(biography);
        setName(name);
        setCollege(college);
        setImage(image);
    }


    public String getBiography() {
        return getString("biography");
    }

    public void setBiography(String biography) {
        put("biography", biography);
    }

    public String getUsername() {
        return getString("username");
    }

    public void setUsername(String username) {
        put("username", username);
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getCollege() {
        return getString("college");
    }

    public void setCollege(String college) {
        put("college", college);
    }

    public ParseUser getUser(){
        return getParseUser("user");
    }
    public void setUser(ParseUser user){
        put("user",user);
    }

    public ParseFile getImage() {
        return getParseFile("profile_picture");
    }

    public void setImage(ParseFile parseFile) {
        put("profile_picture", parseFile);
    }
}
