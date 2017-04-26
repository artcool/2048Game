package com.sinwao.a2048game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 描述：该项目是前段时间很火的2048游戏
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private int score = 0;

    public MainActivity(){
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
    }

    public void clearScore(){
        score = 0;
        showScore();
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public void showScore(){
        tvScore.setText(score + "");
    }

    private static MainActivity mainActivity = null;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
