package com.simplifyi.simplify_phase1.Fragment;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.simplifyi.simplify_phase1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfileFragment extends Fragment
{

    TextView dialedcount,answercount,language,location;
    SimpleRatingBar ratingBar;
    JSONObject userPofileJsonObj;
    JSONArray userDetailsArray;
    Geocoder geocoder;
    List<Address> addresses;
    public ProfileFragment()
    {

    }

    public static ProfileFragment newInstnce(String parm1,String parm2)
    {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        addresses = new ArrayList<>();
        dialedcount = view.findViewById(R.id.text_dialedcount_no);
        answercount = view.findViewById(R.id.text_answere_count_no);
        language = view.findViewById(R.id.textlanguage);
        location = view.findViewById(R.id.text_location);
        ratingBar = view.findViewById(R.id.ratingbar);
        try {
            userPofileJsonObj = new JSONObject(loadJSONFromAsset(getActivity()));
            Log.e("userDetails"," "+userPofileJsonObj.toString());
            userDetailsArray = userPofileJsonObj.getJSONArray("User_Details");
            Log.e("userArray"," "+userDetailsArray+" "+userDetailsArray.length());
            dialedcount.setText("15");
            answercount.setText("18");
            language.setText("English");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            geocoder = new Geocoder(getActivity(), Locale.getDefault());
            addresses = geocoder.getFromLocation(Double.parseDouble("20.5937"),  Double.parseDouble("78.9629"), 1);
            Log.e("addess"," "+addresses);
            if(addresses !=null)
            {
            String country = addresses.get(0).getCountryName();
                Log.e("country"," "+country);
             location.setText(country);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ratingBar.setRating(Float.valueOf("3.5"));
        return view;
    }


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("user_profile.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
