package com.jesusrojo.dagger2demo.di.smartphonedi;

import android.util.Log;

import com.jesusrojo.dagger2demo.smartphone.model.MemoryCard;

import dagger.Module;
import dagger.Provides;

@Module
public class MemoryCardModule {

    private int memorySize;

    public MemoryCardModule(int memorySize) {
        this.memorySize = memorySize;
    }

    @Provides
    //static
    MemoryCard provideMemoryCard(){
        Log.d("##", "memorySize "+memorySize);
        return new MemoryCard();
    }
}
// We use @Module with  @Provides when we can not access to the constructor of the class
// We imaging that we can not access to the MemoryCard class.
// We also need to add to this Module to the SmartPhoneComponent
// @Component(modules = {MemoryCardModule.class})
//public interface SmartPhoneComponent {...}