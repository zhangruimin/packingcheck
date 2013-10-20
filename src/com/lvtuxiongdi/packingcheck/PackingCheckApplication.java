
package com.lvtuxiongdi.packingcheck;

import android.app.Application;
import com.lvtuxiongdi.packingcheck.db.DatabaseHandler;

public class PackingCheckApplication extends Application{

    private DatabaseHandler databaseHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHandler = new DatabaseHandler(this);
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
}
