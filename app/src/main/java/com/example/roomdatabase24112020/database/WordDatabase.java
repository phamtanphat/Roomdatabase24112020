package com.example.roomdatabase24112020.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = WordEntity.class , version = 1)
public abstract class WordDatabase extends RoomDatabase {
    public abstract WordDao wordDao();

    private static WordDatabase instance;

    public static synchronized WordDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context,
                    WordDatabase.class,
                    "WordDatabase"
            )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
