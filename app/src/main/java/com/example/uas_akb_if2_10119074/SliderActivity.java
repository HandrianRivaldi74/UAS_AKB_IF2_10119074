package com.example.uas_akb_if2_10119074;
/**
 * Nama : Handrian Rivaldi
 * Kelas : IF2
 * NIM :10119074
 * Email : handrianr28@gmail.com
 * **/
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager.widget.ViewPager;

import com.example.uas_akb_if2_10119074.adapter.PageAdapter;

public class SliderActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button btnNext;
    int[] layouts;
    PageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        viewPager = findViewById(R.id.pager);
        btnNext = findViewById(R.id.nextBtn);

        layouts = new int[] {
                R.layout.activity_slider1,
                R.layout.activity_slider2,
                R.layout.activity_slider3
        };

        adapter = new PageAdapter(this, layouts);
        viewPager.setAdapter(adapter);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewPager.getCurrentItem()+1<layouts.length){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }else{
                    //kembali ke main activity
                    startActivity(new Intent(SliderActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        });

        viewPager.addOnPageChangeListener(viewPagerChangeListener);
    }

    ViewPager.OnPageChangeListener viewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            if(i == layouts.length -1 ){
                btnNext.setText("nice");
            }else {
                btnNext.setText("go");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
