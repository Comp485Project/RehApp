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

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class OtherUserProfileActivity extends AppCompatActivity {
    public static final String TAG = "OtherUserProfile";
    private Button back_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otheruserprofile);
        String other_user = ""; // TODO

        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.whereEqualTo("username", other_user);
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
                ParseFile profile_pic = profile.get(0).getImage();
                loadImages( profile_pic, image);
            }
        });

        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButton();
            }
        });
    }

    private void backButton() {
        Intent i = new Intent(this, HomeActivity.class);
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
