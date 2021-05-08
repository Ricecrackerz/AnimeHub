package com.example.animehub.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animehub.LoginActivity;
import com.example.animehub.R;
import com.example.animehub.models.Post;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    private static final Object KEY_USER ="userId";

    public Profile() {
        // Required empty public constructor
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

        TextView tvSignout2 = view.findViewById(R.id.tvSignout2);
        TextView tvUser = view.findViewById(R.id.tvUser);
        TextView tvBio = view.findViewById(R.id.tvBio);

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

    }
}