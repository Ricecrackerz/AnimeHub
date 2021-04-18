package com.example.animehub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.animehub.fragments.Discussion;
import com.example.animehub.fragments.Home;
import com.example.animehub.fragments.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        final FragmentManager fragmentManager = getSupportFragmentManager();


    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment ;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment = new Home();
                        Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_discussion:
                        fragment = new Discussion();
                        Toast.makeText(MainActivity.this, "Discussion!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new Profile();
                        Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        //set default as home
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}