package com.example.roomdatabase24112020.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase24112020.database.WordEntity;
import com.example.roomdatabase24112020.respository.WordRespository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WordViewModel extends AndroidViewModel {
    private MutableLiveData<List<WordEntity>> mutableWords;
    private MutableLiveData<Integer> mutableStatusWords;
    private MutableLiveData<Throwable> mutableError;
    private WordRespository wordRespository;

    public WordViewModel(@androidx.annotation.NonNull Application application) {
        super(application);
        mutableWords = new MutableLiveData<>();
        mutableStatusWords = new MutableLiveData<>();
        mutableError = new MutableLiveData<>();
        wordRespository = WordRespository.getInstance(application.getBaseContext());
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

    public void insertWord(WordEntity wordEntity){
        wordRespository.insertWord(wordEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mutableStatusWords.setValue(1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mutableError.setValue(e);
                    }
                });
    }

    public LiveData<Integer> getStatusQuery(){
        return mutableStatusWords;
    }
}
