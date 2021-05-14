package com.example.animehub.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animehub.LoginActivity;
import com.example.animehub.PostsAdapter;
import com.example.animehub.R;
import com.example.animehub.models.Post;
import com.example.animehub.models.User;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    private static final Object KEY_USER ="userId";
    public static final String TAG = "Profile";
    private RecyclerView rvPost;
    private PostsAdapter adapter;
    private List<Post> allPosts;
    private Context context;
    private List<User> users;
    private SwipeRefreshLayout swipeContainer;
    private TextView tvBio;

    public Profile() {
        // Required empty public constructor
    }
    public Profile(Context context, List<User> users){
        this.context = context;
        this.users = users;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //User user = new User();

        TextView tvSignout2 = view.findViewById(R.id.tvSignout2);
        TextView tvUser = view.findViewById(R.id.tvUser);
        TextView tvBio = view.findViewById(R.id.tvBio);

        rvPost = view.findViewById(R.id.rvPost);
        swipeContainer = view.findViewById(R.id.swipeContainer);

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "fetching new data!");
                queryPost();
            }
        });

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        tvBio = view.findViewById(R.id.tvBio);


        tvUser.setText(ParseUser.getCurrentUser().getUsername());
//        tvBio.setText(ParseUser.getCurrentUser().getBio());

        tvSignout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
                goLoginActivity();
                Toast.makeText(getContext(), "Log Out Success!", Toast.LENGTH_SHORT).show();
            }

            private void goLoginActivity() {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }

        });

        // Set the adapter on the recycler view
        rvPost.setAdapter(adapter);

        // Set the Layout Manager
        //rvPosts.setLayoutManager(new FrameLayoutManager(getContext()));
        rvPost.setLayoutManager(new LinearLayoutManager(getContext()));

        /*System.out.println("hi" + user.getBio());
        tvBio.setText(user.getBio());*/

        queryPost();


    }

    private void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        ParseQuery<User> sup = ParseQuery.getQuery(User.class);
        sup.include(Post.KEY_USER);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "Post:" + post.getDescription() + ", username:" + post.getUser().getUsername());
                }
                allPosts.clear();
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });
        sup.findInBackground(new FindCallback<User>() {
            @Override
            public void done(List<User> users, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(User user: users){
                    Log.i(TAG, "User:" + user.getBio());
                }
            }
        });
    }
}