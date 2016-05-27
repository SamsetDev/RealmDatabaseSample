package com.samset.realmdatabaseexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by samset on 27/05/16.
 */
public class MyApplication extends Application {

   private RealmConfiguration config;
   @Override
    public void onCreate() {
        super.onCreate();
       config = new RealmConfiguration.Builder(this).build();
       Realm.setDefaultConfiguration(config);


    }


}
