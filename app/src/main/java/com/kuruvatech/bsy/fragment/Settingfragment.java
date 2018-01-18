package com.kuruvatech.bsy.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.kuruvatech.bsy.App;
import com.kuruvatech.bsy.MainActivity;
import com.kuruvatech.bsy.R;
import com.kuruvatech.bsy.utils.Constants;

import java.util.Locale;

/**
 * Created by dayas on 02-12-2017.
 */

public class Settingfragment extends Fragment {
    Switch switchLanguage;
    private AdView mAdView;
    private static boolean isEnglish = true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.settinglayout, container, false);
//        ((MainActivity) getActivity())
//                .setActionBarTitle("Invite Friends");
        MobileAds.initialize(getActivity(), Constants.ADMOBAPPID);
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        switchLanguage = (Switch) view.findViewById(R.id.language);
        switchLanguage.setChecked(!isEnglish);
        switchLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {


                    ((App) getActivity().getApplication()).setLocale(new Locale("kn"));
                    isEnglish=false;
                } else {
                    isEnglish=true;
                    ((App) getActivity().getApplication()).setLocale(new Locale("en"));

                }
                refreshUI();
            }
        });
        ((MainActivity) getActivity())
                .setActionBarTitle(getString(R.string.titletext));
        return view;
    }
    private ProgressDialog mProgressDialog;

    public void createProgressBar() {

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Signing........");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);
    }

    private void refreshUI() {
       // mProgressDialog.show();
        getActivity().recreate();
       // mProgressDialog.hide();
    }


    //    public void setLocale(String lang) { //call this in onCreate()
//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
//
//    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
