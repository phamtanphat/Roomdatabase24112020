package com.example.roomdatabase24112020.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;

import com.example.roomdatabase24112020.R;
import com.example.roomdatabase24112020.database.WordEntity;
import com.example.roomdatabase24112020.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel wordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        //Observe data
        wordViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                Log.d("BBB",wordEntities.size() + "");
            }
        });

        wordViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Log.d("BBB",throwable.getMessage());
            }
        });

        // Call method
        wordViewModel.selectWords();

    }
}