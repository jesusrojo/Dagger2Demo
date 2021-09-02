package com.jesusrojo.dagger2demo.smartphone.model;

import android.util.Log;

import javax.inject.Inject;

public class NickelCadmiumBattery implements Battery {
    private static final String TAG = "SmartPhone";

    @Inject
    public NickelCadmiumBattery() { }

    @Override
    public String showType() {
        String msg = "Nickel Cadmium Battery... ##";
        Log.d(TAG, msg);
        return msg;
    }
}
