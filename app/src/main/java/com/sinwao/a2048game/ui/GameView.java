package com.sinwao.a2048game.ui;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.sinwao.a2048game.MainActivity;
import com.sinwao.a2048game.numcard.Card;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：游戏主类
 * --------个人简介-------
 * QQ:710760186
 * Email：yibin479@yahoo.com
 * Created by 阿酷 on 2017/4/25.
 */

public class GameView extends GridLayout {

    private static final String TAG = "GameView";

    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }

    /**
     * 游戏初始化化
     */
    private void initGameView(){
        setColumnCount(4);

        /**
         * 设置游戏画布背景
         */
        setBackgroundColor(0xff014252);

        setOnTouchListener(new OnTouchListener() {
            /**
             * 初始化坐标，偏移量
             */
            private float startX,startY,offsetX,offsetY;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        startY = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = motionEvent.getX() - startX;
                        offsetY = motionEvent.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            Log.d(TAG,"往左右滑动");
                            if (offsetX < -5) {
                                Log.d(TAG,"往左滑动");
                                slipLeft();

                            } else if (offsetX > 5) {
                                Log.d(TAG,"往右滑动");
                                slipRight();
                            }

                        } else {
                            Log.d(TAG,"往上下滑动");
                            if (offsetY < -5) {
                                Log.d(TAG,"往上滑动");
                                slipUp();
                            } else if (offsetY > 5) {
                                Log.d(TAG,"往下滑动");
                                slipDown();
                            }
                        }

                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        /**
         * 适配各种屏幕
         */
        int cardWidth = (Math.min(w,h) - 20) / 4;   //获得每一张卡片的宽高
        addCards(cardWidth,cardWidth);

        startGame();
    }

    /**
     * 添加数字卡片对象
     * @param cardWidth
     * @param cardHeight
     */
    private void addCards(int cardWidth,int cardHeight){

        Card card;

        for (int y = 0; y < 4; y++) {
            for (int x = 0;x < 4; x++) {
                card = new Card(getContext());
                card.setNum(0);
                addView(card,cardWidth,cardHeight);
                cardsMap[x][y] = card;
            }
        }
    }

    /**
     * 下滑操作
     */
    private void slipDown() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y >= 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
            checkCpmlete();
        }

    }

    /**
     * 上滑操作
     */
    private void slipUp() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardsMap[x][y1].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
                            cardsMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x][y1])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
            checkCpmlete();
        }

    }

    /**
     * 右滑操作
     */
    private void slipRight() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x++;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
            checkCpmlete();
        }

    }

    /**
     * 左滑操作
     */
    private void slipLeft() {

        boolean merge = false;

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardsMap[x1][y].getNum() > 0) {

                        if (cardsMap[x][y].getNum() <= 0) {
                            cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
                            cardsMap[x1][y].setNum(0);
                            x--;
                            merge = true;
                        } else if (cardsMap[x][y].equals(cardsMap[x1][y])){
                            cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
                            cardsMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardsMap[x][y].getNum());
                            merge = true;
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandom();
            checkCpmlete();
        }
    }

    /**
     * 二维数组存储卡片
     */
    private Card[][] cardsMap = new Card[4][4];

    private List<Point> emptyPoint = new ArrayList<>();

    /**
     * 添加随机数
     */
    private void addRandom() {
        emptyPoint.clear();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() <= 0) {
                    emptyPoint.add(new Point(x,y));
                }

            }
        }
        Point p = emptyPoint.remove((int) (Math.random() * emptyPoint.size()));
        cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }

    /**
     * 开始游戏
     * 1、清空游戏坐标点
     * 2、随机添加两个坐标点
     */
    private void startGame(){
        MainActivity.getMainActivity().clearScore();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                cardsMap[x][y].setNum(0);
            }
        }
        addRandom();
        addRandom();

    }

    /**
     * 检查游戏是否结束
     */
    private void checkCpmlete(){
        boolean isComplete = true;
        ALL:
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (cardsMap[x][y].getNum() == 0 ||
                        (x > 0 && cardsMap[x][y].equals(cardsMap[x-1][y])) ||
                        (x < 3 && cardsMap[x][y].equals(cardsMap[x+1][y])) ||
                        (y > 0 && cardsMap[x][y].equals(cardsMap[x][y-1]))||
                        (y < 3 && cardsMap[x][y].equals(cardsMap[x][y+1]))){
                    isComplete = false;
                    break ALL;
                }
            }
        }
        if (isComplete) {
            Toast.makeText(getContext(),"You lose  it",Toast.LENGTH_SHORT).show();
        }
    }
}
