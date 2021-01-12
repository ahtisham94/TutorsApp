package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.tutorsapp.R;

public class SearchActivity extends AppCompatActivity {
    private Button searchBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        searchBtn = findViewById(R.id.searchBtn);
        Intent intent = new Intent(SearchActivity.this, SearchNearbyActivity.class);
        startActivity(intent);
    }
}