package com.simplifyi.simplify_phase1.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.simplifyi.simplify_phase1.Fragment.ActivityFragment;
import com.simplifyi.simplify_phase1.Fragment.AddFragment;
import com.simplifyi.simplify_phase1.Fragment.EarnFragment;
import com.simplifyi.simplify_phase1.Fragment.LogoFragment;
import com.simplifyi.simplify_phase1.Fragment.ProfileFragment;
import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class BottomNavigation extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_navigation);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        init();

    }
    private void init() {
        bottomNavigationView = findViewById(R.id.bottomBar);
        loadFragment(new LogoFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.ic_logo:
                    fragment = new LogoFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.ic_description:
                    fragment = new ActivityFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.ic_circle:
                    fragment = new AddFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.ic_earn:
                    fragment = new EarnFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.ic_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
