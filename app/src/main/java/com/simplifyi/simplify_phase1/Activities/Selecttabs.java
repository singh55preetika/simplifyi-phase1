package com.simplifyi.simplify_phase1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.simplifyi.simplify_phase1.R;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Selecttabs extends AppCompatActivity {
    private Button btnContinue;
    String f = "hiii";
    String x = "";
    Button toggle1,toggle2,toggle3,toggle4,toggle5,toggle6,toggle7,toggle8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecttabs);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        init();

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(Selecttabs.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);
        toggle1=(Button)findViewById(R.id.one);
        toggle2=(Button)findViewById(R.id.two);
        toggle3=(Button)findViewById(R.id.three);
        toggle4=(Button)findViewById(R.id.four);
        toggle5=(Button)findViewById(R.id.five);
        toggle6=(Button)findViewById(R.id.six);
        toggle7=(Button)findViewById(R.id.seven);
        toggle8=(Button)findViewById(R.id.eight);


    }

    public void caller(View view) {
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        int pos = spin.getSelectedItemPosition();
        if (pos == 0) {
            f = "English";
        }
        if (pos == 1) {
            f = "Kannada";
        }

        if (pos == 2) {
            f = "Malayalam";
        }
        if (pos == 3) {
            f = "Hindi";
        }
        if (pos == 4) {
            f = "Telugu";
        }
        x = x + " , " + f;
        display(x);
    }
public void xxxxx(View v)
{
    Intent intent = new Intent(this, WaitingForOtp.class);
    startActivity(intent);
}
    public void display(String f) {
        TextView x = (TextView) findViewById(R.id.textView);
        x.setText("the selected languages are:"+f);
    }

    public void init() {

        btnContinue = findViewById(R.id.btn_continue);

        btnContinue = (Button) findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Selecttabs.this, Answercall.class);
                startActivity(intent);
            }
        });
    }
    int cond=2,cond1=2,cond2=2,cond3=2,cond4=2,cond5=2,cond6=2,cond7=2;
    public void changecolour1(View v)
    {

        if(cond%2==0) {
            cond++;
            toggle1.setBackgroundResource(R.drawable.button_background_grey);

        }
       else
        {
            cond++;

            toggle1.setBackgroundResource(R.drawable.button_background_orange);
        }
    }
    public void changecolour2(View v)
    {

        if(cond1%2==0) {
            cond1++;
            toggle2.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond1++;
            toggle2.setBackgroundResource(R.drawable.button_background_orange);

        }
    }

        public void changecolour3(View v)
        {

            if(cond2%2==0) {
                cond2++;
            toggle3.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond2++;
            toggle3.setBackgroundResource(R.drawable.button_background_orange);

        }
    }
    public void changecolour4(View v)
    {

        if(cond3%2==0) {
            cond3++;
            toggle4.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond3++;
            toggle4.setBackgroundResource(R.drawable.button_background_orange);


        }
    }
        public void changecolour5(View v)
        {

        if(cond4%2==0) {
            cond4++;
            toggle5.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond4++;
            toggle5.setBackgroundResource(R.drawable.button_background_orange);

        }
        }
        public void changecolour6(View v)
         {

        if(cond5%2==0) {
            cond5++;
            toggle6.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond5++;
            toggle6.setBackgroundResource(R.drawable.button_background_orange);

        }
         }
    public void changecolour7(View v)
    {

        if(cond6%2==0) {
            cond6++;
            toggle7.setBackgroundResource(R.drawable.button_background_grey);

        }
        else
        {
            cond6++;
            toggle7.setBackgroundResource(R.drawable.button_background_orange);

        }
    }
    public void changecolour8(View v)
    {

        if(cond7%2==0) {
            cond7++;
            toggle8.setBackgroundResource(R.drawable.button_background_grey);


        }
        else
        {
            cond7++;
            toggle8.setBackgroundResource(R.drawable.button_background_orange);

        }
    }


}
