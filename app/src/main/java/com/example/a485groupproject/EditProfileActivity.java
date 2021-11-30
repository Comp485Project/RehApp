package com.example.a485groupproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    public static final String TAG = "EditProfileActivity";

    private ImageView profileImageView;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    private EditText nameTextField;
    private EditText usernameTextField;
    private EditText schoolTextField;
    private EditText bioTextField;
    private Button backButton;
    private Button updateButton;
    private Button selectImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        nameTextField = findViewById(R.id.name);
        usernameTextField = findViewById(R.id.username);
        schoolTextField = findViewById(R.id.school);
        bioTextField = findViewById(R.id.bio);

        backButton = findViewById(R.id.back_button);
        updateButton = findViewById(R.id.update_button);
        selectImageButton = findViewById(R.id.select_photo);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backButtonGo();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // create new profile object
                Profile profile = new Profile();
                profile.put("user", ParseUser.getCurrentUser());
                if(nameTextField.getText().toString().length() > 0)
                    profile.put("name", nameTextField.getText().toString());
                if(usernameTextField.getText().toString().length() > 0)
                    profile.put("username", usernameTextField.getText().toString());
                if(schoolTextField.getText().toString().length() > 0)
                    profile.put("college", schoolTextField.getText().toString());
                if(bioTextField.getText().toString().length() > 0)
                    profile.put("biography", bioTextField.getText().toString());
                try {
                    profile.save();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                ParseQuery<Profile> query = ParseQuery.getQuery(Profile.class);
                query.include("user");
                query.whereEqualTo("user", ParseUser.getCurrentUser());
                query.getFirstInBackground(new GetCallback<Profile>() {
                    @Override
                    public void done(Profile profile, ParseException e) {
                        if (e != null) {
                            Log.e(TAG, "Issue with getting user's profile", e);
                            return;
                        }
                        // updating it with new data
                        profile.put("user", ParseUser.getCurrentUser());
                        if(nameTextField.getText().toString().length() > 0)
                        profile.put("name", nameTextField.getText().toString());
                        if(usernameTextField.getText().toString().length() > 0)
                        profile.put("username", usernameTextField.getText().toString());
                        if(schoolTextField.getText().toString().length() > 0)
                        profile.put("college", schoolTextField.getText().toString());
                        if(bioTextField.getText().toString().length() > 0)
                        profile.put("biography", bioTextField.getText().toString());
                        profile.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Saved successfully.
                                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                } else {
                                    // The save failed.
                                    Toast.makeText(getApplicationContext(), "Failed to Save Profile", Toast.LENGTH_SHORT).show();
                                    Log.d(getClass().getSimpleName(), "User update error: " + e);
                                }
                            }
                        });
                    }
                });
            }
        });

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImageButtonGo();
            }
        });

    }

    private void backButtonGo() {
        Intent i = new Intent(this, UserProfileActivity.class);
        startActivity(i);
        finish();
    }

    private void selectImageButtonGo() {
            // create an instance of the
            // intent of the type image
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);

            // pass the constant to compare it
            // with the returned requestCode
            startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
        }

        // this function is triggered when user
        // selects the image from the imageChooser
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == RESULT_OK) {

                // compare the resultCode with the
                // SELECT_PICTURE constant
                if (requestCode == SELECT_PICTURE) {
                    // Get the url of the image from data
                    Uri selectedImageUri = data.getData();
                    if (null != selectedImageUri) {
                        // update the preview image in the layout
                        profileImageView.setImageURI(selectedImageUri);
                        Log.i("Image View ", selectedImageUri.toString());
                    }
                }
            }


        }
    }
