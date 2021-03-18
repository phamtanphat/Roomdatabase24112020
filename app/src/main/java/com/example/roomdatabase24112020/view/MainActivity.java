package com.example.roomdatabase24112020.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.roomdatabase24112020.R;
import com.example.roomdatabase24112020.constants.DatabaseConstants;
import com.example.roomdatabase24112020.database.WordEntity;
import com.example.roomdatabase24112020.viewmodel.WordViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel wordViewModel;
    String mMessage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        //Observe data

        // 1 : Insert thanh cong

//        wordViewModel.getStatusQuery().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                switch (integer){
//                    case DatabaseConstants.STATUS_INSERT_SUCCESS :
//                        mMessage = "Thêm thành công";
//                        break;
//                    case DatabaseConstants.STATUS_UPDATE_SUCCESS :
//                        mMessage = "Cập nhật thành công";
//                        break;
//                }
//                Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
//            }
//        });
        wordViewModel.getWords().observe(this, new Observer<List<WordEntity>>() {
            @Override
            public void onChanged(List<WordEntity> wordEntities) {
                for (int i = 0; i < wordEntities.size(); i++) {
                    Log.d("BBB",wordEntities.get(i).getId() + "");
                }
            }
        });

        wordViewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Call method
        wordViewModel.selectWords();
//        wordViewModel.insertWord(new WordEntity("Two","Hai",false));




//        wordViewModel.updateWord(true , 1);
    }
}