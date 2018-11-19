package com.simplifyi.simplify_phase1.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class WaitingForOtp extends AppCompatActivity {
    private Button btnContinue;
    private EditText eterotp;
    public TextView mobile;
int d=0;
public TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_for_otp);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
//        mobile=(TextView)findViewById(R.id.textView5);
//        b fu=new b();
//        mobile.setText(fu.func());
        init();
        timer=(TextView)findViewById(R.id.timerText);
        CountDownTimer count=new CountDownTimer(40*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
               timer.setText("00:"+(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                timer.setText("00:00");

            }
        }.start();
    }
public void backing(View view)
{
    Intent intent = new Intent(WaitingForOtp.this, RegisterPage.class);
    startActivity(intent);
}

    public void init() {

        eterotp = findViewById(R.id.eterotp);
        btnContinue = findViewById(R.id.btn_continue);

        btnContinue = (Button) findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (d == 1) {
                    Intent intent = new Intent(WaitingForOtp.this, UserDetailsActivity.class);
                    startActivity(intent);
                }
            }
        });


        eterotp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.e("otpLenght", " " + charSequence.length());
                d=1;

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.length() == 0 || editable.length() < 4|| editable.length() > 4) {
                    eterotp.setError("Enter Valid OTP");
                    btnContinue.setEnabled(false);
                    btnContinue.setBackgroundResource(R.drawable.button_bg_rounded_corners);
                } else {
                    InputMethodManager btnchange = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    btnchange.hideSoftInputFromWindow(eterotp.getWindowToken(), 0);
                    btnContinue.setEnabled(true);
                    btnContinue.setBackgroundResource(R.drawable.button_background_orange);
                }

            }
        });
    }


}

