
package com.lvtuxiongdi.packingcheck;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.lvtuxiongdi.packingcheck.db.DatabaseHandler;
import com.lvtuxiongdi.packingcheck.model.Packing;

public class PackingCheckApplication extends Application{

    private static final String INIT_PREF = "initPref";
    private static final String IS_INITIALIZED_KEY = "isInitializedKey";
    private DatabaseHandler databaseHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHandler = new DatabaseHandler(this);
        initData();
    }

    private void initData() {
        SharedPreferences sharedPreferences = getSharedPreferences(INIT_PREF, Context.MODE_PRIVATE);
        boolean isInitialized = sharedPreferences.getBoolean(IS_INITIALIZED_KEY, false);
        if(!isInitialized){
            insertInitData();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean(IS_INITIALIZED_KEY, true);
            edit.commit();
        }
    }

    private void insertInitData() {
        databaseHandler.addPacking(new Packing(2, "去爬山", R.drawable.ic_hiking));
        databaseHandler.addPacking(new Packing(3, "Item 3", R.drawable.ic_launcher));
        databaseHandler.addPacking(new Packing(4, "Item 4", R.drawable.ic_launcher));
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
}
