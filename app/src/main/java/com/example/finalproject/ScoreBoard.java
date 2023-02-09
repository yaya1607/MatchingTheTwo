package com.example.finalproject;

public class ScoreBoard {
    String userName;
    int easyScore;
    int mediumScore;
    int hardScore;
    int sumScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEasyScore() {
        return easyScore;
    }

    public void setEasyScore(int easyScore) {
        this.easyScore = easyScore;
    }

    public int getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(int mediumScore) {
        this.mediumScore = mediumScore;
    }

    public int getHardScore() {
        return hardScore;
    }

    public void setHardScore(int hardScore) {
        this.hardScore = hardScore;
    }

    public int getSumScore() {
        return sumScore;
    }

    public void setSumScore(int sumScore) {
        this.sumScore = sumScore;
    }

    public ScoreBoard(String userName, int easyScore, int mediumScore, int hardScore, int sumScore) {
        this.userName = userName;
        this.easyScore = easyScore;
        this.mediumScore = mediumScore;
        this.hardScore = hardScore;
        this.sumScore = sumScore;
    }
    public ScoreBoard(){
        this.userName = "Unknown";
        this.easyScore = 0;
        this.mediumScore = 0;
        this.hardScore=0;
    }
}
