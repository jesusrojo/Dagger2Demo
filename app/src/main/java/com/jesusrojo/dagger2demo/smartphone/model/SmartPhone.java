package com.jesusrojo.dagger2demo.smartphone.model;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SmartPhone {

    private Battery battery;
    private MemoryCard memoryCard;
    private SIMCard simCard;
    private static final String TAG = "SmartPhone";
    private String time;

    @Inject
    public SmartPhone(Battery battery, MemoryCard memoryCard, SIMCard simCard) {
        this.battery = battery;
        this.memoryCard = memoryCard;
        this.simCard = simCard;
        Log.d("##", battery.showType());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:MM:ss");
        time = simpleDateFormat.format(Calendar.getInstance().getTime());
    }


    public String makeACall(){
        String msg = "making a call......... ##";
        Log.d("##", msg);
        Log.d("##", "created at " +time);
        return msg;
    }

    public String getBatteryType() {
        return battery.showType();
    }
}
