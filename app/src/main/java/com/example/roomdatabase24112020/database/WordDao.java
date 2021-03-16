package com.example.roomdatabase24112020.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface WordDao {

    // Get data
    @Query("SELECT * FROM WordEntity ")
    Single<List<WordEntity>> getWords();

    // Insert
    @Insert(entity = WordEntity.class , onConflict = OnConflictStrategy.IGNORE)
    Completable insertWord(WordEntity wordEntity);
}
