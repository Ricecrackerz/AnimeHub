package com.example.animehub;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnCreate;
    private Button btnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnCreate = findViewById(R.id.btnCreate);

        TextView tvgoBack = findViewById(R.id.tvGoBack);
        String loginText = "Already have an account? Sign In.";

        SpannableString mSpannableString = new SpannableString(loginText);
        ForegroundColorSpan mBlue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan mWhite = new ForegroundColorSpan(Color.WHITE);
        mSpannableString.setSpan(mWhite, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSpannableString.setSpan(mBlue, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mSpannableString.setSpan(new UnderlineSpan(), 25, 32, 0);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(SignUpActivity.this, "Back to Main Menu!", Toast.LENGTH_SHORT).show();
                goLoginActivity();

            }
        };

        mSpannableString.setSpan(clickableSpan, 25, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvgoBack.setText(mSpannableString);
        tvgoBack.setMovementMethod(LinkMovementMethod.getInstance());

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Username cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password = etPassword.getText().toString();
                if(password.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                createAccount(username, password);
            }
        });


        /*tvgoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this, "Back to Main Menu!", Toast.LENGTH_SHORT).show();
                goLoginActivity();
            }
        });*/


    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void createAccount(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUpActivity.this, "Created account successfully!", Toast.LENGTH_SHORT).show();
                    goMainActivity();
                }
                else {
                    ParseUser.logOut();
                    Toast.makeText(SignUpActivity.this, "Account already created!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
