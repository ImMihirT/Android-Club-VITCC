package com.qnext.mac.home_automation;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.content.BroadcastReceiver;
import android.telephony.SmsManager;
import android.Manifest.permission;


import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class Control_Bar extends AppCompatActivity  {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private CardView cv2,cv3,cv1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


       // int count=0;
        String e="Hello";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control__bar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.dl);
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cv2=(CardView)findViewById(R.id.cv2);
        cv3=(CardView)findViewById(R.id.cv3);
        cv1=(CardView)findViewById(R.id.cv1);


            if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);

            }

      cv2.setOnClickListener(new View.OnClickListener() {

int ct=0;
            @Override

            public void onClick(View view){
                try {

                    SmsManager sms = SmsManager.getDefault();
                    if(ct%2==0) {


                        sms.sendTextMessage("9462762232", null, "Fan On", null, null);
                        Toast.makeText(Control_Bar.this,"Switched On!",Toast.LENGTH_LONG).show();

                    }
                    else{
                        sms.sendTextMessage("9462762232", null, "Fan Off", null, null);
                        Toast.makeText(Control_Bar.this,"Switched Off!",Toast.LENGTH_LONG).show();

                    }
                       // Toast.makeText(Control_Bar.this,"SMS Successfully sent!",Toast.LENGTH_LONG).show();
                    ct++;

                }

                catch (Exception e){
                    Toast.makeText(Control_Bar.this,"An error occured. Please try again!",Toast.LENGTH_LONG).show();
                }
            }


        });
        cv3.setOnClickListener(new View.OnClickListener() {
            int cnt=0;
            @Override
            public void onClick(View view){
                try {

                    SmsManager sms = SmsManager.getDefault();
                    if(cnt%2==0) {


                        sms.sendTextMessage("9462762232", null, "L1 On", null, null);
                        Toast.makeText(Control_Bar.this,"Switched On!",Toast.LENGTH_LONG).show();

                    }
                    else{
                        sms.sendTextMessage("9462762232", null, "L1 Off", null, null);
                        Toast.makeText(Control_Bar.this,"Switched Off!",Toast.LENGTH_LONG).show();

                    }
                    cnt++;
                 //   Toast.makeText(Control_Bar.this,"SMS Successfully sent!",Toast.LENGTH_LONG).show();


                }catch (Exception e){
                    Toast.makeText(Control_Bar.this,"An error occured. Please try again!",Toast.LENGTH_LONG).show();
                }
            }


        });
        cv1.setOnClickListener(new View.OnClickListener() {
            int count=0;
            @Override
            public void onClick(View view){
                try {

                    SmsManager sms = SmsManager.getDefault();
                    if(count%2==0) {


                        sms.sendTextMessage("9462762232", null, "L2 On", null, null);
                        Toast.makeText(Control_Bar.this,"Switched On!",Toast.LENGTH_LONG).show();

                    }
                    else{
                        sms.sendTextMessage("9462762232", null, "L2 Off", null, null);
                        Toast.makeText(Control_Bar.this,"Switched Off!",Toast.LENGTH_LONG).show();

                    }
                    count++;
                   // Toast.makeText(Control_Bar.this,"SMS Successfully sent!",Toast.LENGTH_LONG).show();


                }catch (Exception e){
                    Toast.makeText(Control_Bar.this,"An error occured. Please try again!",Toast.LENGTH_LONG).show();
                }
            }


        });



    }

    public boolean onOptionsItemsSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);



    }
/*
    @Override
    public void onClick(View view) {
        if(view == cv1)
        {
            SmsManager sms = SmsManager.getDefault();
            try{
                sms.sendTextMessage("7550171051", null, "Hello", null, null);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(Control_Bar.this, "Failed to send SMS",
                        Toast.LENGTH_SHORT).show();
            }


        }
        if(view == cv2)
        {
            SmsManager sms = SmsManager.getDefault();
            try{
                sms.sendTextMessage("7550171051", null, "Hello", null, null);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(Control_Bar.this, "Failed to send SMS",
                        Toast.LENGTH_SHORT).show();
            }
        }
        if(view == cv3)
        {
            SmsManager sms = SmsManager.getDefault();
            try{
                sms.sendTextMessage("7550171051", null, "Hello", null, null);

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(Control_Bar.this, "Failed to send SMS",
                        Toast.LENGTH_SHORT).show();
            }
        }
        }*/
}
