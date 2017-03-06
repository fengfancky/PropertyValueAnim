package com.example.chen.propertyvalueanim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mText,mText1,mText2;
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText= (TextView) findViewById(R.id.text);
        mText1= (TextView) findViewById(R.id.text1);
        mText2= (TextView) findViewById(R.id.text2);
        mText.setOnClickListener(this);
        mText1.setOnClickListener(this);
        mText2.setOnClickListener(this);
        mainLayout= (RelativeLayout) findViewById(R.id.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                drawParabola();
                break;
            case R.id.text1:
                drawSine();
                break;
            case R.id.text2:
                drawHreat();
                break;
        }
    }
    private void drawParabola(){
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 800f);
        animation.setDuration(2000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //设置目标对象的位置
                float translateX=(float)animation.getAnimatedValue();
                //y=x^2/1000
                mText.setTranslationX(translateX);
                mText.setTranslationY((2*translateX*translateX/1000));

                //绘制一个小点表示当前位置，形成目标对象的运动轨迹
                View view =new View(MainActivity.this);
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(5,5);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(R.drawable.para_line_shape);
                view.setTranslationX(translateX);
                view.setTranslationY((2*translateX*translateX/1000));
                mainLayout.addView(view);
            }
        });
        animation.start();
    }
    private void drawSine(){
        ValueAnimator animation = ValueAnimator.ofFloat(0f, 628f);
        animation.setDuration(2000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float translateX=(float)animation.getAnimatedValue();
                //y=400*sin(x/100)
                mText1.setTranslationX(translateX);
                mText1.setTranslationY((float) (400*Math.sin(translateX/100)));

                View view =new View(MainActivity.this);
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(5,5);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(R.drawable.sine_line_shape);
                view.setTranslationX(translateX);
                view.setTranslationY((float) (400*Math.sin(translateX/100))+dip2px(200,MainActivity.this));
                mainLayout.addView(view);
            }
        });
        animation.start();
    }
    private void drawHreat(){
        ValueAnimator animation = ValueAnimator.ofInt(0, 360);
        animation.setDuration(6000);
        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int angle=(int)animation.getAnimatedValue();
                //x=a*(2*cos(t)-cos(2*t));
                //y=a*(2*sin(t)-sin(2*t))
                //变换
                //y=a*(-2*cos(t)+cos(2*t));
                //x=a*(-2*sin(t)+sin(2*t))
                double t=2*3.14/360*angle;//弧度制
                mText2.setTranslationY((float) (100*(-2*Math.cos(t)+Math.cos(2*t))));
                mText2.setTranslationX((float) (100*(-2*Math.sin(t)+Math.sin(2*t))));

                View view =new View(MainActivity.this);
                RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(5,5);
                view.setLayoutParams(layoutParams);
                view.setBackgroundResource(R.drawable.heart_line_shape);
                view.setTranslationY((float) (100*(-2*Math.cos(t)+Math.cos(2*t)))+dip2px(200,MainActivity.this));
                view.setTranslationX((float) (100*(-2*Math.sin(t)+Math.sin(2*t)))+dip2px(200,MainActivity.this));
                mainLayout.addView(view);
            }
        });
        animation.start();
    }
    public  int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}