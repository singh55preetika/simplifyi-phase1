package com.simplifyi.simplify_phase1.Activities;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.simplifyi.simplify_phase1.R;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.anwarshahriar.calligrapher.Calligrapher;


public class CallActivity extends AppCompatActivity {

    private static final String APP_KEY = "cc7f241b-f0ba-47d2-82fc-34516e4e016d";
    private static final String APP_SECRET = "Keyc6Lwzr0OwdA8Tnhx0zw==";
    private static final String ENVIRONMENT = "sandbox.sinch.com";
    private static final String URL = "http://www.mocky.io/v2/5beb9fc53300007d00fbbf97";

    int c=0;
    private Call call;
    private TextView callState;
    private SinchClient sinchClient;
    private Button button;
    private String callerId;
    private String recipientId;
    private RequestQueue mQueue;
    private TextView callerDetails;
    private TextView recieverDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call);
        Calligrapher myfront=new Calligrapher(this);
        myfront.setFont(this,"roboto.xml",true);
        handleSSLHandshake();
        mQueue  = Volley.newRequestQueue(this);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                callerDetails = (TextView) findViewById(R.id.callerTextView);
                recieverDetails =  (TextView) findViewById(R.id.reciverTextView);

                try {
                    recipientId= response.getString("reciver");
                    recieverDetails.setText(response.getString("reciver"));
                    callerId  = response.getString("caller");
                    callerDetails.setText(response.getString("caller"));
                    sinchClient = Sinch.getSinchClientBuilder()
                            .context(getBaseContext())
                            .userId(callerId)
                            .applicationKey(APP_KEY)
                            .applicationSecret(APP_SECRET)
                            .environmentHost(ENVIRONMENT)
                            .build();

                    sinchClient.setSupportCalling(true);
                    sinchClient.startListeningOnActiveConnection();
                    sinchClient.start();
                    sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener());
                    button = (Button) findViewById(R.id.button);
                    callState = (TextView) findViewById(R.id.callState);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (call == null) {
                                call = sinchClient.getCallClient().callUser(recipientId);
                                call.addCallListener(new SinchCallListener());
                                button.setText("Hang Up");
                            } else {
                                c=1;
                                call.hangup();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }
    public String formatTime(long millis) {
        String output = "";
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours=minutes/ 60;

        seconds = seconds % 60;
        minutes = minutes % 60;
        hours=hours%60;

        String secondsD = String.valueOf(seconds);
        String minutesD = String.valueOf(minutes);
        String hoursD = String.valueOf(hours);


        if (seconds < 10)
            secondsD = "0" + seconds;
        if (minutes < 10)
            minutesD = "0" + minutes;

        if (hours < 10)
            hoursD = "0" + hours;

        output = minutesD + " : " + secondsD;
        return output;
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }
    private class SinchCallListener implements CallListener {


        public void onCallEnded(Call endedCall) {
            call = null;
            SinchError a = endedCall.getDetails().getError();
            final TextView timer1;
            timer1=(TextView)findViewById(R.id.timer);

//            button.setVisibility(View.GONE);
            button.setText("Call");
            callState.setText("");
            setVolumeControlStream(AudioManager.USE_DEFAULT_STREAM_TYPE);
            timer1.setText("done!");
        }

        @Override
        public void onCallEstablished(Call establishedCall) {

            callState.setText("connected");
            final TextView timer1;
            timer1=(TextView)findViewById(R.id.timer);
            setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);
            CountDownTimer countDownTimer = new CountDownTimer(180 * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    if(c==0) {
                        timer1.setText(formatTime(millisUntilFinished));
                    }
                }
                public void onFinish() {
                    timer1.setText("Done !");
                    c=1;
                    call.hangup();


                }
            };
            countDownTimer.start();
        }

        @Override
        public void onCallProgressing(Call progressingCall) {
            callState.setText("ringing");
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> pushPairs) {
        }
    }

    private class SinchCallClientListener implements CallClientListener {
        @Override
        public void onIncomingCall(CallClient callClient, Call incomingCall) {
            call = incomingCall;
            Toast.makeText(CallActivity.this, "incoming call", Toast.LENGTH_SHORT).show();
            call.answer();
            call.addCallListener(new SinchCallListener());
            button.setVisibility(View.VISIBLE);
            button.setText("Hang Up");

        }
    }


}

