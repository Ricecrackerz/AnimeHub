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

import com.bumptech.glide.Glide;
import com.example.animehub.fragments.Discussion;
import com.example.animehub.models.Comment;
import com.example.animehub.models.Post;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

import bolts.Task;

public class DiscussionDetailPage extends AppCompatActivity {

    public static final String TAG = "DiscussionDetailPage";
    private TextView tvDescription, tvTitle, tvUsername, tvSpoiler1;
    private ImageView ivProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);

        Bundle bundle = getIntent().getExtras();
        tvDescription = findViewById(R.id.tvDescription);
        tvTitle = findViewById(R.id.tvTitle);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvUsername = findViewById(R.id.tvUsername);
        tvSpoiler1 = findViewById(R.id.tvSpoiler1);


        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String username = getIntent().getStringExtra("username");
        String response = getIntent().getStringExtra("spoilers");
        ParseFile image = getIntent().getParcelableExtra("profilePic");



        if(image != null) {
            Glide.with(getBaseContext()).load(image.getUrl()).into(ivProfileImage);
        }//if
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvUsername.setText(username);

        if(response.equals("Yes")){
            tvSpoiler1.setText("SPOILERS!!!");
        }
        if(response.equals("No")){
            tvSpoiler1.setText("No Spoilers :))");
        }


    }

}