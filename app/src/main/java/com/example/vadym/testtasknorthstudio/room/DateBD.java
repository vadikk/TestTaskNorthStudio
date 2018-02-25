package com.example.vadym.testtasknorthstudio.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vadym.testtasknorthstudio.model.DateModel;

/**
 * Created by Vadym on 21.02.2018.
 */
@Database(entities = {DateModel.class}, version = 2)
public abstract class DateBD extends RoomDatabase {

    private static final String DB_NAME = "date.db";
    private static DateBD instance;

    public static DateBD getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DateBD.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    public abstract DateDao dateDao();
}
