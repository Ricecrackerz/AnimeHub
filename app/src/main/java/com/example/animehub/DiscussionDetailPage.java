package com.example.animehub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animehub.fragments.Discussion;
import com.example.animehub.models.Comment;
import com.example.animehub.models.Post;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import bolts.Task;

public class DiscussionDetailPage extends AppCompatActivity {

    public static final String TAG = "DiscussionDetailPage";
    private TextView tvDescription, tvTitle, tvUsername;
    private ImageView ivProfileImage;
    private Button btnComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);

        Bundle bundle = getIntent().getExtras();
        tvDescription = findViewById(R.id.tvDescription);
        tvTitle = findViewById(R.id.tvTitle);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvUsername = findViewById(R.id.tvUsername);

        btnComment = findViewById(R.id.btnComment);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String username = getIntent().getStringExtra("username");

        if(bundle != null){
            int profilePic = bundle.getInt("profilePic");
            ivProfileImage.setImageResource(profilePic);
        }
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvUsername.setText(username);

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDialogBox();
            }
        });

        queryPost();


    }

    private void queryPost() {
        ParseQuery<Comment> query = ParseQuery.getQuery(Comment.class);
        //query.include(Post.KEY_POST);
        query.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> comment, ParseException e) {
                if(e != null){
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for(Comment comments: comment){
                    Log.i(TAG, "Comments: " + comments.getDescription() + "post: " + comments.getPost().getObjectId());
                }
            }
        });
    }

    private void goDialogBox() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        final EditText etCommentTitle = new EditText(this);
        layout.addView(etCommentTitle);
        etCommentTitle.setHint("Title");

        final EditText eCommentDescription = new EditText(this);
        layout.addView(eCommentDescription);
        eCommentDescription.setHint("Description");

        final Button btnSubmit = new Button(this);
        layout.addView(btnSubmit);
        btnSubmit.setText("SUBMIT");

        builder.setView(layout);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = eCommentDescription.getText().toString();
                String title = etCommentTitle.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(DiscussionDetailPage.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(title.isEmpty()){
                    Toast.makeText(DiscussionDetailPage.this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                ParseQuery<Comment> postQuery = ParseQuery.getQuery(Comment.class);

                ParseObject currentPost = checkPost();

                //ParseObject currentPost = ParseObject.getObjectId();
                //currentPost =
                ParseUser currentUser = ParseUser.getCurrentUser();
                //System.out.println(currentUser);
                //ParseObject currentPost = ParseObject.getObjectId();
                //savePost(description,currentUser,title);
                savePost(description, currentUser, title, currentPost);
                alertDialog.dismiss();
            }
        });
    }

    private ParseObject checkPost() {

        ParseQuery<Comment> postQuery = ParseQuery.getQuery(Comment.class);
        postQuery.include("postID");
        final ParseObject[] currentPost = new ParseObject[1];
        postQuery.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> objects, ParseException e) {
                currentPost[0] = objects.get(Integer.parseInt("postID"));
            }
        });
        /*ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("Comment");
        //userQuery.include("postID");
        //final ParseObject[] currentPost = new ParseObject[1];
        //Task <ParseObject> currentPost = new Task<>();
        //userQuery.find("postID");

        userQuery.getInBackground("<PARSE_OBJECT_ID>", (object, e) -> {
            currentPost[0] = (ParseObject) object.get("postID");
            System.out.println("hi" + currentPost[0]);
            if (e == null) {
                //Object was successfully retrieved
                currentPost[0] = (ParseObject) object.get("postID");
                System.out.println("hi" + currentPost[0]);
                //currentPost[0] = object.getParseObject("postID");
                //System.out.println(currentPost[0]);
            } else {
                // something went wrong
                System.out.println(":(");
                Toast.makeText(DiscussionDetailPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        /*userQuery.findInBackground(new FindCallback<Comment>() {
            @Override
            public void done(List<Comment> objects, ParseException e) {
                if (e == null) {
                    // userObjects retrieved successfully
                    currentPost[0] = (ParseObject) objects.get(Integer.parseInt("postID"));
                } else {
                    Log.d("objects", "Error: " + e.getMessage());
                }
            }
        });*/
        System.out.println(currentPost[0]);

        return currentPost[0];

    }

    private void savePost(String description, ParseUser currentUser, String title, ParseObject currentPost) {
        Comment comment = new Comment();
        comment.setDescription(description);
        comment.setUser(currentUser);
        comment.setTitle(title);
        System.out.println(title);

        //ParseObject currentPost = currentUser.getParseObject("PostID");
        comment.setPost(currentPost);
        System.out.println(currentPost);
        //post.setPost(currentPost);
        comment.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Log.e(TAG, "Error while saving", e);
                    Toast.makeText(DiscussionDetailPage.this, "Error while saving!", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "Post save was successful");
            }
        });
    }
}