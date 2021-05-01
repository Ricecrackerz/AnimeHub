package com.example.animehub.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animehub.MainActivity;
import com.example.animehub.PostsAdapter;
import com.example.animehub.R;
import com.example.animehub.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class Discussion extends Fragment {


    public static final String TAG = "Discussion";
    private RecyclerView rvPosts;
    private Button btnDiscussion;
    private EditText etDescription, etTitle;
    private PostsAdapter adapter;
    private List<Post> allPosts;

    public Discussion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discussion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPosts = view.findViewById(R.id.rvPosts);
        btnDiscussion = view.findViewById(R.id.btnDiscussion);

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        btnDiscussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDialogBox();
            }
        });
        
        // Set the adapter on the recycler view
        rvPosts.setAdapter(adapter);

        // Set the Layout Manager
        //rvPosts.setLayoutManager(new FrameLayoutManager(getContext()));
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        queryPost();
    }

    private void queryPost() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
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
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void goDialogBox() {

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        final EditText etTitle = new EditText(getActivity());
        layout.addView(etTitle);
        etTitle.setHint("Title");

        final EditText etDescription = new EditText(getActivity());
        layout.addView(etDescription);
        etDescription.setHint("Description");

        final Button btnSubmit = new Button(getActivity());
        layout.addView(btnSubmit);
        btnSubmit.setText("SUBMIT");

        builder.setView(layout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        /*View popupView = getLayoutInflater().inflate(R.layout.activity_post, null);
        PopupWindow popupWindow = new PopupWindow(popupView,
        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);
        etTitle = (EditText) view.findViewById(R.id.etTitle);
        etDescription = (EditText) popupView.findViewById(R.id.etDescription);
        Button btnSubmit =  popupView.findViewById(R.id.btnSubmit);


        If you need the PopupWindow to dismiss when when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                afterTextChanged(number);
            }
        });

        AlertDialog alertDialog = AlertDialog.create();
        alertDialog.show();*/



           btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                String title = etTitle.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(title.isEmpty()){
                    Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, currentUser, title);
            }
        });

    }

    private void savePost(String description, ParseUser currentUser, String title) {
        Post post = new Post();
        post.setDescription(description);
        post.setTitle(title);
        post.setUser(currentUser);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(getContext(), "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post save was successful");
                etDescription.setText("");
                etTitle.setText("");
            }
        });
    }
}