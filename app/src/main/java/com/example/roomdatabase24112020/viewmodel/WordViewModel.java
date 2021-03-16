package com.example.roomdatabase24112020.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase24112020.database.WordEntity;
import com.example.roomdatabase24112020.respository.WordRespository;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordViewModel extends ViewModel {
    private MutableLiveData<List<WordEntity>> mutableWords;
    private MutableLiveData<Throwable> mutableError;
    private WordRespository wordRespository;

    public WordViewModel(Context context) {
        mutableWords = new MutableLiveData<>();
        mutableError = new MutableLiveData<>();
        wordRespository = WordRespository.getInstance(context);
    }

    public void selectWords(){
        wordRespository.getWords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<WordEntity>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<WordEntity> wordEntities) {
                        mutableWords.setValue(wordEntities);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mutableError.setValue(e);
                    }
                });
    }

    public LiveData<List<WordEntity>> getWords(){
        return mutableWords;
    }

    public LiveData<Throwable> getError(){
        return mutableError;
    }
}
