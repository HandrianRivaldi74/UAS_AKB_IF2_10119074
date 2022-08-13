package com.example.uas_akb_if2_10119074;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Menghilangkan Action Bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread thread = new Thread(){
            public  void run(){
                try {
                    sleep(1500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashActivity.this, SliderActivity.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
