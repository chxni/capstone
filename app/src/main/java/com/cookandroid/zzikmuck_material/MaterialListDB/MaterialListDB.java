package com.cookandroid.zzikmuck_material.MaterialListDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MaterialList.class}, version = 1)
public abstract class MaterialListDB extends RoomDatabase {  //룸 데이터베이스 상속받음

    private static MaterialListDB mlDB;
    public synchronized static MaterialListDB getInstance(Context context)
    {
        if (mlDB == null)
        {
            mlDB = Room.databaseBuilder(context.getApplicationContext(), MaterialListDB.class, "zzikmuck")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mlDB;
    }

    public abstract MaterialListDAO materialDao();
}
