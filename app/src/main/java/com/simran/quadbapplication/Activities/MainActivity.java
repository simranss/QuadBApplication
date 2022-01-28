package com.simran.quadbapplication.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.simran.quadbapplication.Fragments.HomeFragment;
import com.simran.quadbapplication.R;
import com.simran.quadbapplication.Fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        SearchFragment searchFragment = new SearchFragment();

        showFragment(homeFragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bot_nav_home:
                    showFragment(homeFragment);
                    return true;
                case R.id.bot_nav_search:
                    showFragment(searchFragment);
                    return true;
                default:
                    return false;
            }
        });
    }

    void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frag_container, fragment);
        transaction.commit();
    }
}