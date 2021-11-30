package com.example.a485groupproject;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class Post extends ParseObject {
    public static final String KEY_TEXT = "description";
    public static final String CREATED_KEY = "createdAt";
    public static final String KEY_URGENCY_RATING = "urgencyRating";
    public static final String KEY_USER = "author";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_PRIVACY = "Privacy";
    public static final String KEY_NAME = "username";
    public static final String KEY_IMAGE = "image";

    public ParseFile getKeyImage(){ return getParseFile(KEY_IMAGE); }
    public void setKeyImage(ParseFile file){ put(KEY_IMAGE, file); }

    public String getKeyText(){ return getString(KEY_TEXT); }
    public void setKeyText(String postText){ put(KEY_TEXT, postText); }

    public int getCreatedKey(){ return getInt(CREATED_KEY); }

    public int getKeyUrgencyRating(){ return getInt(KEY_URGENCY_RATING); }
    public void setKeyUrgencyRating(int rating){ put(KEY_URGENCY_RATING, rating); }

    public ParseUser getKeyUser(){ return getParseUser(KEY_USER); }
    public void setKeyUser(ParseUser user){ put(KEY_TEXT, user); }

    public String getKeyCategory(){ return getString(KEY_CATEGORY); }
    public void setKeyCategory(String category){ put(KEY_TEXT, category); }

    public String getKeyPrivacy(){ return getString(KEY_PRIVACY); }
    public void setKeyPrivacy(String privacy){ put(KEY_TEXT, privacy); }

    public String getKeyName(){ return getString(KEY_NAME); }
    public void setKeyName(String name){ put(KEY_NAME, name); }

}
