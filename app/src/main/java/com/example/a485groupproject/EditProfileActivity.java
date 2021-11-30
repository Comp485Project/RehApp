package com.example.a485groupproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    public static final String TAG = "EditProfileActivity";

    private ImageView profileImageView;

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
                updateButtonGo();
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

    private void updateButtonGo() {

    }

    private void selectImageButtonGo() {
        // TODO
    }
}
