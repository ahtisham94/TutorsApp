package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.CancelledAssignmentAdapter;
import com.example.tutorsapp.models.CancelledAssignmentModel;

import java.util.ArrayList;
import java.util.List;

public class CancelledAssignmentActivity extends AppCompatActivity {
    private RecyclerView cancelledAssignmentRl;

    private List<CancelledAssignmentModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelled_assignment);
        cancelledAssignmentRl = findViewById(R.id.cancelledAssignmentRl);

        initData();

        ImageView backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CancelledAssignmentActivity.super.onBackPressed();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        CancelledAssignmentAdapter adapter = new CancelledAssignmentAdapter(list, this);
        cancelledAssignmentRl.setLayoutManager(new LinearLayoutManager(this));
        cancelledAssignmentRl.setAdapter(adapter);
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new CancelledAssignmentModel("Assignment Code 001", "14/03/2020", "Teacher"));
        list.add(new CancelledAssignmentModel("Assignment Code 002", "14/03/2020", "Teacher"));
        list.add(new CancelledAssignmentModel("Assignment Code 003", "14/03/2020", "Teacher"));
        list.add(new CancelledAssignmentModel("Assignment Code 004", "14/03/2020", "Teacher"));
    }
}