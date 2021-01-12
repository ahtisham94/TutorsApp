package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.AssignmentHistoryRecyclerViewAdapter;
import com.example.tutorsapp.models.AssignmentHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<AssignmentHistoryModel> assigmentHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        recyclerView = findViewById(R.id.recyclerView);

        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        AssignmentHistoryRecyclerViewAdapter adapter = new AssignmentHistoryRecyclerViewAdapter(assigmentHistoryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        assigmentHistoryList = new ArrayList<>();
        assigmentHistoryList.add(new AssignmentHistoryModel(1, "S.NO. 001", "20/05/2020", "Paid 5,000", "Done"));
        assigmentHistoryList.add(new AssignmentHistoryModel(2, "S.NO. 002", "22/01/2020", "Paid 5,000", "Done"));
        assigmentHistoryList.add(new AssignmentHistoryModel(3, "S.NO. 003", "22/01/2020", "Paid 5,000", "Done"));
        assigmentHistoryList.add(new AssignmentHistoryModel(4, "S.NO. 004", "22/01/2020", "Paid 5,000", "Done"));
    }
}
