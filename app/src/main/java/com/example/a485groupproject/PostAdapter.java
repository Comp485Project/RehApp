package com.example.a485groupproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<Post> allPosts;

    public PostAdapter(Context context, List<Post> allPosts){
        this.context = context;
        this.allPosts = allPosts;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = allPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView userPic;
        TextView postCategory;
        TextView postUsername;
        TextView postText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.userPic);
            postCategory = itemView.findViewById(R.id.post_category);
            postUsername = itemView.findViewById(R.id.users_id);
            postText = itemView.findViewById(R.id.post_text);
        }
        public void bind(Post post){
            postUsername.setText(post.getKeyUser().getUsername());
            postCategory.setText(post.getKeyCategory());
            postText.setText(post.getKeyText());
            if(userPic != null){
                Glide.with(context).load(post.getKeyImage().getUrl()).into(userPic);
            }
        }

    }
}
