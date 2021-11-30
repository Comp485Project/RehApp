package com.example.a485groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class ComposeActivity extends AppCompatActivity {

    private Spinner dropBox;
    private Spinner categoryBox;
    private Spinner urgencyBox;
    private EditText composeText;
    private Button postButton;
    private TextView profileName;
    private ImageView profilePic;
    private Button backButton;
    private DropBoxAdapter adapter;     //public or private option
    private CategoryAdapter categoryAdapter;
    private UrgencyRatingAdapter urgentAdapter;

    public ComposeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        ParseUser user = ParseUser.getCurrentUser();
        dropBox = findViewById(R.id.publicOrPrivate);
        categoryBox = findViewById(R.id.postCategory);
        urgencyBox = findViewById(R.id.urgencyRate);
        composeText = findViewById(R.id.composeText);
        profileName = findViewById(R.id.profileName);
        profilePic = findViewById(R.id.userPic);
        backButton = findViewById(R.id.backButton);
        postButton = findViewById(R.id.post);

        adapter = new DropBoxAdapter(this);
        dropBox.setAdapter((SpinnerAdapter) adapter);

        categoryAdapter = new CategoryAdapter(this);
        categoryBox.setAdapter((SpinnerAdapter) categoryAdapter);

        urgentAdapter = new UrgencyRatingAdapter(this);
        urgencyBox.setAdapter((SpinnerAdapter) urgentAdapter);

        profileName.setText(user.getUsername());
        //profilePic.setImageURI();

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = composeText.getText().toString();
                if(text.isEmpty()){
                    Toast.makeText(ComposeActivity.this, "Post cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                savePost(text, user);
            }
        });
    }

    private void savePost(String postText, ParseUser user){
        
    }
}