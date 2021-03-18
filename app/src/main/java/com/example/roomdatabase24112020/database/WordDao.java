package com.example.roomdatabase24112020.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface WordDao {

    // Get data
    @Query("SELECT * FROM WordEntity ORDER BY id DESC LIMIT :rowCount , :totalItems")
    Single<List<WordEntity>> getWords(int rowCount , int totalItems);

    // Insert
    @Insert(entity = WordEntity.class , onConflict = OnConflictStrategy.IGNORE)
    Completable insertWord(WordEntity wordEntity);

    @Query("UPDATE WordEntity SET isMemorized = :isMemorized WHERE id = :id")
    Maybe<Integer> updateWord(boolean isMemorized ,long id);

    @Query("DELETE FROM WordEntity WHERE id = :id")
    Maybe<Integer> deleteWord(long id);

}
