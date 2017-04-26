package com.sinwao.a2048game.numcard;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 描述：把2,4,8,16，...，2048等数字一一抽象成对象
 * --------个人简介-------
 * QQ:710760186
 * Email：yibin479@yahoo.com
 * Created by 阿酷 on 2017/4/25.
 */

public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x55ffffff);
        LayoutParams layoutParams = new LayoutParams(-1, - 1);
        layoutParams.setMargins(10,10,0,0);
        addView(label,layoutParams);
        setNum(0);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }

    }

    public boolean equals(Card card) {
        return getNum() == card.getNum();
    }

    private TextView label;
}
