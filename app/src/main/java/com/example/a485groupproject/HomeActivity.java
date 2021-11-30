package com.example.a485groupproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = "HomeActivity";
    private static final int REQUEST_CODE = 20;
    private RecyclerView rvPosts;
    protected PostAdapter postAdapter;
    protected List<Post> allPosts;
    private Button createPost;
    private Button profileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createPost = findViewById(R.id.create_post);
        profileButton = findViewById(R.id.profile_button);

        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPost();
            }
        });

        allPosts = new ArrayList<>();
        rvPosts = findViewById(R.id.rvRehAppPosts);
        postAdapter = new PostAdapter(this, allPosts);

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(postAdapter);
        queryPosts();

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProfile();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.compose){
            Intent intent = new Intent(this, ComposeActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void queryPosts(){
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo("Privacy", "Public");
        query.setLimit(20);
        query.addDescendingOrder(Post.CREATED_KEY);

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e!=null){
                    Log.e(TAG, "Issue with getting posts");
                    return;
                }
                allPosts.addAll(objects);
                postAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createPost() {
        Intent i = new Intent(this, ComposeActivity.class);
        startActivity(i);
        finish();
    }

    private void goProfile() {
        Intent i = new Intent(this, UserProfileActivity.class);
        startActivity(i);
        finish();
    }

    private void goOtherUserProfile() {
        Intent i = new Intent(this, OtherUserProfileActivity.class);
        startActivity(i);
        finish();
    }
}