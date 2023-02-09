package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

public class ScoreBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        createScoreBoard();
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        createScoreBoard();
    }

    @SuppressLint("ResourceAsColor")
    private void createScoreBoard() {
        Database database = new Database(this, "MatchingTheTwo.sqlite", null, 1);
        Cursor scoreBoard = database.GetData("SELECT * FROM Scoreboard");

        Score[] scoresList = new Score[scoreBoard.getCount()];
        while (scoreBoard.moveToNext()) {
            String userName = scoreBoard.getString(1);
            int easyScore = scoreBoard.getInt(2),
                    mediumScore = scoreBoard.getInt(3),
                    hardScore = scoreBoard.getInt(4);
            scoresList[scoreBoard.getPosition()] = new Score(userName, easyScore, mediumScore, hardScore, easyScore + mediumScore + hardScore);
        }

        Arrays.sort(scoresList, new Comparator<Score>() {
            @Override
            public int compare(Score player1, Score player2) {
                if (player1.sumScore < player2.sumScore) return 1;
                else if (player1.sumScore > player2.sumScore) return -1;
                else return 0;
            }
        });

        TableLayout tbl = (TableLayout) findViewById(R.id.tbScore);

        for (int i = 0; i < scoresList.length; i++) {
            TableRow tbr = new TableRow(this);
            TextView tvName = new TextView(this),
                    tvEasyScore = new TextView(this),
                    tvMediumScore = new TextView(this),
                    tvHardScore = new TextView(this),
                    tvSumScore = new TextView(this);

            tvName.setText(scoresList[i].userName);
            tvEasyScore.setText(String.valueOf(scoresList[i].easyScore));
            tvMediumScore.setText(String.valueOf(scoresList[i].mediumScore));
            tvHardScore.setText(String.valueOf(scoresList[i].hardScore));
            tvSumScore.setText(String.valueOf(scoresList[i].sumScore));

            tvName.setGravity(Gravity.CENTER);
            tvEasyScore.setGravity(Gravity.CENTER);
            tvMediumScore.setGravity(Gravity.CENTER);
            tvHardScore.setGravity(Gravity.CENTER);
            tvSumScore.setGravity(Gravity.CENTER);

            tvName.setTextColor(R.color.black);
            tvEasyScore.setTextColor(R.color.black);
            tvMediumScore.setTextColor(R.color.black);
            tvHardScore.setTextColor(R.color.black);
            tvSumScore.setTextColor(R.color.black);


            tbr.addView(tvName);
            tbr.addView(tvEasyScore);
            tbr.addView(tvMediumScore);
            tbr.addView(tvHardScore);
            tbr.addView(tvSumScore);

            tbl.addView(tbr);
        }

    }
}