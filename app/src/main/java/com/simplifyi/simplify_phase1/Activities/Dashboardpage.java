package com.simplifyi.simplify_phase1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Dashboardpage extends AppCompatActivity {
    private Button but;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dashboardpage);
            Calligrapher myfront=new Calligrapher(this);
            myfront.setFont(this,"roboto.xml",true);
             init();
        }

    private void init() {
        but=(Button)findViewById(R.id.setting);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboardpage.this, Selecttabs.class);
                startActivity(intent);
            }
        });
    }

    }



