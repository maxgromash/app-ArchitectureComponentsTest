package com.example.componentstest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Title.class}, version = 1, exportSchema = false)
public abstract class TitleDatabase extends RoomDatabase {
    private static String DB_NAME = "titles.db";
    private static TitleDatabase database;

    public static TitleDatabase getInstance(Context context) {
        if (database == null)
            database = Room.databaseBuilder(context, TitleDatabase.class, DB_NAME).build();
        return database;
    }

    public abstract TitleDao titleDao();
}