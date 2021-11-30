package com.example.a485groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignInActivity extends AppCompatActivity {

    public static final String TAG = "SignInActivity";
    private EditText username;
    private EditText password;
    private Button forgotButton;
    private Button signInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        if(ParseUser.getCurrentUser() != null){
            goHomeTimeline();
        }

        username = findViewById(R.id.emailTextField);
        password = findViewById(R.id.passwordTextField);
        forgotButton = findViewById(R.id.forgotButton);
        signInButton = findViewById(R.id.signInButton);
        signUpButton = findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick signIn button");
                String user_username = username.getText().toString();
                String user_password = password.getText().toString();
                signInUser(user_username, user_password);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));

            }
        });

        forgotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,ForgotPasswordActivity.class));
            }
        });
    }

    private void signInUser(String username, String password) {
        Log.i(TAG, "Attempting to login user " + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Toast.makeText(getApplicationContext(), "Issue with login", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Issue with login", e);
                    return;
                }
                //TODO: navigate to the main activity if the user has signed in properly
                goHomeTimeline();
                Toast.makeText(SignInActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goHomeTimeline() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

}
