package com.example.a485groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class ComposeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner dropBox;
    private Spinner categoryBox;
    private Spinner urgencyBox;
    private EditText composeText;
    private Button postButton;
    private TextView profileName;
    private ImageView profilePic;
    private Button backButton;
    //private DropBoxAdapter adapter;     //public or private option
    //private CategoryAdapter categoryAdapter;
    //private UrgencyRatingAdapter urgentAdapter;



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

        String[] privacyOptions = getResources().getStringArray(R.array.postOptions);
        ArrayAdapter privacyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, privacyOptions);
        dropBox.setAdapter(privacyAdapter);

        String[] categoryOptions = getResources().getStringArray(R.array.category);
        ArrayAdapter categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryOptions);
        categoryBox.setAdapter(categoryAdapter);

        String[] urgencyOptions = getResources().getStringArray(R.array.urgency);
        ArrayAdapter urgencyAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, urgencyOptions);
        urgencyBox.setAdapter(urgencyAdapter);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}