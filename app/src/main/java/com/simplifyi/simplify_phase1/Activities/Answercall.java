package com.simplifyi.simplify_phase1.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Answercall extends AppCompatActivity {
private Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answercall);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        init();
    }
    public void monaco(View view)
    {
        Intent in=new Intent(this,LaunchCallActivity.class);
        startActivity(in);
    }
    private void init() {
        but=(Button)findViewById(R.id.setting);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Answercall.this, Selecttabs.class);
                startActivity(intent);
            }
        });
    }
    public void pehle(View v)
    {
        Intent intent = new Intent(Answercall.this, Callfunction.class);
        startActivity(intent);
    }
}
