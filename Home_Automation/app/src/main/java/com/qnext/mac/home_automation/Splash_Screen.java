  package com.qnext.mac.home_automation;

import android.app.Activity;
import android.content.Intent;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

  public class Splash_Screen extends AppCompatActivity {
private TextView tv;
      private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        tv=(TextView)findViewById(R.id.textView2);
        iv=(ImageView)findViewById(R.id.imageView);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(animation);
        iv.startAnimation(animation);
        final Intent I =new Intent(Splash_Screen.this,MainActivity.class);
    Thread timer=new Thread(){
    public void run(){
        try{
            sleep(2000);

        }catch(InterruptedException e){
            e.printStackTrace();

        }
        finally {
            startActivity(I);
            finish();

        }
    }
};
timer.start();
    }
}
