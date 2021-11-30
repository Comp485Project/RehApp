package com.example.a485groupproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class UserProfileActivity extends AppCompatActivity {
    public static final String TAG = "UserProfileActivity";

    private Button editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.include("user");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.setLimit(1);
        query.findInBackground(new FindCallback<Profile>() {
            @Override
            public void done(List<Profile> profile, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting user's profile", e);
                    return;
                }
                    TextView name = findViewById(R.id.name);
                    name.setText(profile.get(0).getName());

                    TextView username = findViewById(R.id.username);
                    username.setText(profile.get(0).getUsername());

                    TextView school = findViewById(R.id.school);
                    school.setText(profile.get(0).getCollege());

                    TextView bio = findViewById(R.id.bio);
                    bio.setText(profile.get(0).getBiography());

                    ImageView image = findViewById(R.id.profile_picture);
                    ParseFile profilepic = profile.get(0).getImage();
                    loadImages( profilepic, image);
            }
        });

        editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editButton();
            }
        });
    }

    private void editButton() {
        Intent i = new Intent(this, EditProfileActivity.class);
        startActivity(i);
        finish();
    }

    private void loadImages(ParseFile thumbnail, final ImageView img) {

        if (thumbnail != null) {
            thumbnail.getDataInBackground(new GetDataCallback() {
                @Override
                public void done(byte[] data, ParseException e) {
                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                        img.setImageBitmap(bmp);
                    } else {
                    }
                }
            });
        }
    }
}

