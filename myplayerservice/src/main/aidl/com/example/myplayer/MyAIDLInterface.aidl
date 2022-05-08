// MyAIDLInterface.aidl
package com.example.myplayer;

// Declare any non-default types here with import statements

interface MyAIDLInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     int getMaxDuration();
     void start();
     void stop();
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
}