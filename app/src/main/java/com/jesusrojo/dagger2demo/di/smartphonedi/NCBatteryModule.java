package com.jesusrojo.dagger2demo.di.smartphonedi;

import com.jesusrojo.dagger2demo.smartphone.model.Battery;
import com.jesusrojo.dagger2demo.smartphone.model.NickelCadmiumBattery;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NCBatteryModule {

    @Binds
    abstract Battery bindNCBattery(NickelCadmiumBattery nickelCadmiumBattery);

////////v257, m5:40
//    @Provides
//    Battery provideNCBattery2(NickelCadmiumBattery nickelCadmiumBattery){
//
//        nickelCadmiumBattery.showType();
//
//        return nickelCadmiumBattery;
//    }
/////////
}
