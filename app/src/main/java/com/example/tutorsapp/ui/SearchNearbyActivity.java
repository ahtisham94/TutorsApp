package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.tutorsapp.R;

public class SearchNearbyActivity extends AppCompatActivity {
private Button searchBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_nearby);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// pretending keyboard from automatically opening
        initView();
    }

    private void initView() {
        searchBtn=findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchNearbyActivity.this, SearchResultActivity.class);
                startActivity(intent);
            }
        });
        Spinner sortSp = findViewById(R.id.sortSp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sortSp, R.layout.li_spinner);
        adapter.setDropDownViewResource(R.layout.li_spinner);
        sortSp.setAdapter(adapter);
    }
}