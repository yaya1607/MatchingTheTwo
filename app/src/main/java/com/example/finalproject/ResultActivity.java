package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Database database = new Database(this,"MatchingTheTwo.sqlite",null,1);
        Cursor scoreBoard = database.GetData("SELECT * FROM Scoreboard");

        ImageView btnHome = (ImageView) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);
        TextView resultTitle = findViewById(R.id.resultTitle);
        if(getIntent().getIntExtra("Result",0) == 1){
            resultTitle.setText("Congratulate!!!");
        }else{
            resultTitle.setText("Game Over!");
        }
        int score = getIntent().getIntExtra("Score", 0);
        scoreLabel.setText(getString(R.string.result_score, score));

        // High Score
        boolean isExist = false;
        int highEasyScore = 0,
            highMediumScore = 0,
            highHardScore = 0;
        while(scoreBoard.moveToNext()){
            if (getIntent().getStringExtra("UserName").equals(scoreBoard.getString(1))) {
                isExist = true;
                highEasyScore = scoreBoard.getInt(2);
                highMediumScore = scoreBoard.getInt(3);
                highHardScore = scoreBoard.getInt(4);
            }
        }
        switch(getIntent().getStringExtra("Mode")){
            case "Easy":{
                highScoreLabel.setText(getString(R.string.high_score, highEasyScore));
                if(!isExist){

                    database.QueryData("INSERT INTO Scoreboard VALUES (null,'"+getIntent().getStringExtra("UserName")+"',"+score+", 0, 0 )");
                }else if (score > highEasyScore){
                    highScoreLabel.setText(getString(R.string.high_score, score));
                    database.QueryData("UPDATE Scoreboard SET EasyScore = "+ String.valueOf(score) +" WHERE Name ='"+getIntent().getStringExtra("UserName")+"';");
                }break;
            }
            case "Medium":{
                highScoreLabel.setText(getString(R.string.high_score, highMediumScore));
                if(!isExist){
                    database.QueryData("INSERT INTO Scoreboard VALUES (null,'"+getIntent().getStringExtra("UserName")+"', 0,"+score+", 0 )");
                }else if (score > highMediumScore){
                    highScoreLabel.setText(getString(R.string.high_score, score));
                    database.QueryData("UPDATE Scoreboard SET MediumScore = "+ String.valueOf(score) +" WHERE Name ='"+getIntent().getStringExtra("UserName")+"';");
                }break;
            }
            case "Hard":{
                highScoreLabel.setText(getString(R.string.high_score, highHardScore));
                if(!isExist){
                    database.QueryData("INSERT INTO Scoreboard VALUES (null,'"+getIntent().getStringExtra("UserName")+"', 0, 0,"+score+" )");
                }else if (score > highHardScore){
                    highScoreLabel.setText(getString(R.string.high_score, score));
                    database.QueryData("UPDATE Scoreboard SET HardScore = "+ String.valueOf(score) +" WHERE Name ='"+getIntent().getStringExtra("UserName")+"';");
                }break;
            }
        }


    }




}