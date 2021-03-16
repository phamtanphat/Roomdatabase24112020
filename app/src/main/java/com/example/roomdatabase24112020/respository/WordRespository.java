package com.example.roomdatabase24112020.respository;

import android.content.Context;

import com.example.roomdatabase24112020.database.WordDao;
import com.example.roomdatabase24112020.database.WordDatabase;
import com.example.roomdatabase24112020.database.WordEntity;

import java.util.List;

import io.reactivex.Single;

public class WordRespository {
    private static WordRespository instance;
    private WordDao wordDao;

    public WordRespository(Context context) {
        wordDao = WordDatabase.getInstance(context).wordDao();
    }

    public static WordRespository getInstance(Context context){
        if (instance == null){
            instance = new WordRespository(context);
        }
        return instance;
    }

    public Single<List<WordEntity>> getWords(){
        return wordDao.getWords();
    }

}
