package com.simplifyi.simplify_phase1.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.simplifyi.simplify_phase1.R;

import android.os.Handler;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Refresh extends AppCompatActivity {
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_refresh);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        imageView = (ImageView) findViewById(R.id.img_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }
}