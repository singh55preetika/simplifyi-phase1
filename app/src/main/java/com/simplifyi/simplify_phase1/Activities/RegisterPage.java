package com.simplifyi.simplify_phase1.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class RegisterPage extends AppCompatActivity {

    private Button btnContinue;
    public EditText entermobileNo;
    public String s;
public int c=0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        init();
    }
    public void init() {

        entermobileNo = findViewById(R.id.enter_mobileNo);
        btnContinue = (Button)findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                if(c==1){
                Intent intent = new Intent(RegisterPage.this, WaitingForOtp.class);
                startActivity(intent);}
            }
        });


        entermobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("phnoLenght", " " + charSequence.length());

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() == 0 || editable.length() < 10 || editable.length() > 10) {

                    btnContinue.setEnabled(false);
                    btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                } else {
                    InputMethodManager btnchange = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    btnchange.hideSoftInputFromWindow(entermobileNo.getWindowToken(), 0);
                    btnContinue.setEnabled(true);
                    btnContinue.setBackgroundResource(R.drawable.button_background_orange);
                    c=1;
                }


            }
        });
    }

}
