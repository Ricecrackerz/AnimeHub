package com.example.animehub;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animehub.fragments.Discussion;
import com.example.animehub.models.Post;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts){
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvDescription, tvUsername, tvSpoiler;
        private ImageView ivProfileImage;
        private RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            container = itemView.findViewById(R.id.container);
            tvSpoiler = itemView.findViewById(R.id.tvSpoiler);


        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            //tvDescription.setText(post.getDescription());
            tvTitle.setText(post.getTitle());
            tvUsername.setText(post.getUser().getUsername());
            //GETTING PROFILE IMAGE FROM PARSE (idk why i did caps)
            ParseFile image = post.getUser().getParseFile("image");
            if(image != null){
                Glide.with(context).load(image.getUrl()).into(ivProfileImage);
            }

            String response = post.getSpoiler();

            if(response.equals("Yes")){
                tvSpoiler.setText("SPOILERS!!!");
            }
            if(response.equals("No")){
                tvSpoiler.setText("No Spoilers :))");
            }

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Going to details page!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DiscussionDetailPage.class);
                    i.putExtra("title", post.getTitle());
                    i.putExtra("description", post.getDescription());
                    i.putExtra("username", post.getUser().getUsername());
                    i.putExtra("profilePic",  post.getUser().getParseFile("image"));
                    i.putExtra("spoilers", post.getSpoiler());
                    context.startActivity(i);
                    //((Activity) getActivity()).overridePendingTransition(0, 0);
                    //context.finish();

                }
            });

        }

        public void clear() {
            posts.clear();
            notifyDataSetChanged();
        }

        public void addAll(List<Post> posts){
            posts.addAll(posts);
            notifyDataSetChanged();
        }
    }
}
