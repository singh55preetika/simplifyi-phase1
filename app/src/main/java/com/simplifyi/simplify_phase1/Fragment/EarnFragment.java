package com.simplifyi.simplify_phase1.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplifyi.simplify_phase1.R;


public class EarnFragment extends Fragment {


    public EarnFragment() {
        // Required empty public constructor
    }


    public static EarnFragment newInstance(String param1, String param2) {
        EarnFragment fragment = new EarnFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_earn, container, false);
        return view;
    }


}
