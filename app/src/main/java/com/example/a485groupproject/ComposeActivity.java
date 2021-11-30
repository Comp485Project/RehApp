package com.example.a485groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class ComposeActivity extends AppCompatActivity {

    private TextView profileName;
    private Button backButton;
    private Button createPost;
    private EditText description;
    private Switch privacy;

    private RadioButton depression;
    private RadioButton anxiety;
    private RadioButton eating;
    private RadioButton self_harm;
    private RadioButton other;
    private RadioButton substance;

    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private RadioButton four;
    private RadioButton five;

    private String ChosenCategory = "Other";
    private String ChosenPrivacy = "Public";
    private int ChosenRating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);


        profileName = findViewById(R.id.profileName);
        backButton = findViewById(R.id.backButton);
        createPost = findViewById(R.id.postButton);

        description = findViewById(R.id.composeText);
        privacy = findViewById(R.id.public_private);

        depression = findViewById(R.id.depression);
        anxiety = findViewById(R.id.anxiety);
        eating = findViewById(R.id.eating_disorder);
        self_harm = findViewById(R.id.self_harm);
        other = findViewById(R.id.other);
        substance = findViewById(R.id.substance_abuse);

        one = findViewById(R.id.rating1);
        two = findViewById(R.id.rating2);
        three = findViewById(R.id.rating3);
        four = findViewById(R.id.rating4);
        five = findViewById(R.id.rating5);

        // Getting user data
        ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
        query.include("user");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.setLimit(1);
        query.findInBackground(new FindCallback<Profile>() {
            @Override
            public void done(List<Profile> profile, ParseException e) {
                if (e != null) {
                    Log.e("ComposeActivity", "Issue with getting user's profile", e);
                    return;
                }

                profileName.setText(profile.get(0).getName());
                ImageView image = findViewById(R.id.userPic);
                ParseFile profilePic = profile.get(0).getImage();
                loadImages( profilePic, image);
            }
        });

        // Handling Privacy
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                privacy.setText("Private");
                ChosenPrivacy = "Private";
            }
        });

        // Handing categories
        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Depression";
            }
        });

        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Anxiety";
            }
        });

        self_harm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Self harm";
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Other";
            }
        });

        substance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Addiction/Substance Use";
            }
        });

        eating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenCategory = "Eating Disorder";
            }
        });


        // Handling Urgency Rating
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenRating = 1;
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenRating = 2;
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenRating = 3;
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenRating = 4;
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChosenRating = 5;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButtonGo();
            }
        });

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create new Post object
                Post post = new Post();
                post.put("author", ParseUser.getCurrentUser());
                if (description.getText().toString().length() > 0) {
                    post.put("description", description.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Description cannot be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                post.put("category", ChosenCategory);
                post.put("urgencyRating", ChosenRating);
                post.put("Privacy", ChosenPrivacy);
                post.put("username", profileName.getText().toString());
                try {
                    post.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null) {
                            Log.e("ComposeActivity", "Error while saving", e);
                            Toast.makeText(ComposeActivity.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ComposeActivity.this, "Post saved successfully!", Toast.LENGTH_SHORT).show();
                            description.setText("");

                        }
                    }
                });
            }
        });
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

    private void backButtonGo() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}