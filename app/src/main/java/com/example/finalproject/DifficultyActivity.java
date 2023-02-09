package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DifficultyActivity extends AppCompatActivity {
    int result = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void easyMode(View view) {
        Intent i = new Intent(this,GameplayActivity.class);
        i.putExtra("Mode","Easy");
        i.putExtra("UserName",getIntent().getStringExtra("UserName"));
        startActivityForResult(i,result);
    }

    public void mediumMode(View view) {
        Intent i = new Intent(this,GameplayActivity.class);
        i.putExtra("Mode","Medium");
        i.putExtra("UserName",getIntent().getStringExtra("UserName"));
        startActivityForResult(i,result);
    }

    public void hardMode(View view) {
        Intent i = new Intent(this,GameplayActivity.class);
        i.putExtra("Mode","Hard");
        i.putExtra("UserName",getIntent().getStringExtra("UserName"));
        startActivityForResult(i,result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}