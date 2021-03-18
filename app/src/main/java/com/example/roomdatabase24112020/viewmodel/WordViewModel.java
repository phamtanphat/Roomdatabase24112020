package com.example.roomdatabase24112020.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase24112020.constants.DatabaseConstants;
import com.example.roomdatabase24112020.database.WordEntity;
import com.example.roomdatabase24112020.respository.WordRespository;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
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


    public void selectWords(int page , int totalItems){
        wordRespository.getWords(page , totalItems)
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
                        mutableStatusWords.setValue(DatabaseConstants.STATUS_INSERT_SUCCESS);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mutableError.setValue(new Throwable("Thêm dữ liệu thất bại"));
                    }
                });
    }

    public void updateWord(boolean isMemorized ,long id){
        wordRespository.updateWord( isMemorized , id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        if (integer > 0){
                            mutableStatusWords.setValue(DatabaseConstants.STATUS_UPDATE_SUCCESS);
                        }else{
                            mutableError.setValue(new Throwable("Id không tồn tại"));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mutableError.setValue(new Throwable("Cập nhật dữ liệu thất bại"));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void deleteWord(long id){
        wordRespository.deleteWord(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        if (integer > 0){
                            mutableStatusWords.setValue(DatabaseConstants.STATUS_DELETE_SUCCESS);
                        }else{
                            mutableError.setValue(new Throwable("Id không tồn tại"));
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mutableError.setValue(new Throwable("Xóa dữ liệu thất bại"));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<Integer> getStatusQuery(){
        return mutableStatusWords;
    }
}
