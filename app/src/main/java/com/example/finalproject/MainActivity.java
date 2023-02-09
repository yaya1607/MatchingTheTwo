package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database database;
    String strName = "Unknown";
    String strAge;
    int lanchUserActivity = 1;
    int lanchGame = 0;
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        soundPlayer = new SoundPlayer(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        createDatabase();


        Cursor scoreBoard = database.GetData("SELECT * FROM Scoreboard");


        ImageView btnStart = (ImageView) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DifficultyActivity.class);
                intent.putExtra("UserName", strName);
                startActivityForResult(intent, lanchGame);


            }
        });


        ImageView btnLeaderboard = (ImageView) findViewById(R.id.btnLeaderBoard);
        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Leaderboards will be available in the next game updates", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ScoreBoardActivity.class);
                startActivity(intent);
            }
        });


        ImageView btnAccount = (ImageView) findViewById(R.id.btnConfirmUser);
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                startActivityForResult(intent, lanchUserActivity);
            }
        });

    }

    private void createDatabase() {
        database = new Database(this, "MatchingTheTwo.sqlite", null, 1);

        database.QueryData("CREATE TABLE IF NOT EXISTS Scoreboard(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(200), EasyScore INTEGER, MediumScore INTEGER, HardScore INTEGER)");
        //database.QueryData("DELETE FROM Scoreboard WHERE Name = 'Unknown'");
        //database.QueryData("INSERT INTO Scoreboard VALUES (null, 'Triet', 200, 350, 500) ");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) { // Tránh trường hợp chưa có data đã bấm về.
            if (requestCode == lanchUserActivity) {
                strName = data.getStringExtra("UserName");
                strAge = data.getStringExtra("UserAge");
                Toast.makeText(getApplicationContext(), "Welcome " + strName, Toast.LENGTH_LONG).show();

            }
        }

    }
}