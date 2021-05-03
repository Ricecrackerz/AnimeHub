package com.example.animehub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DiscussionDetailPage extends AppCompatActivity {

    private TextView tvDescription, tvTitle, tvUsername;
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


    }
}