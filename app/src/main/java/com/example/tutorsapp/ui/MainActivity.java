package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.MainScreenSliderAdapter;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.GeneralResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

public class MainActivity extends AppCompatActivity {
    private TextView skipProceedTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        SpringDotsIndicator sliderDotspanel = findViewById(R.id.sliderDotspanel);
        skipProceedTv = findViewById(R.id.skipProceedTv);
        //setting viewpage slider
        final MainScreenSliderAdapter viewPagerAdapter = new MainScreenSliderAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        sliderDotspanel.setViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int lastIdx = viewPagerAdapter.getCount() - 1;

                if (position == lastIdx)
                    skipProceedTv.setText(getResources().getText(R.string.proceed));
                else
                    skipProceedTv.setText(getResources().getText(R.string.skip));

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //Setting click listener for skip button
        skipProceedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        });
    }

}