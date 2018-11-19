package com.simplifyi.simplify_phase1.Activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;


public class UploadQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadquestion);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
    }
}
