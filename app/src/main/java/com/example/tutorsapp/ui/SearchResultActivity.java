package com.example.tutorsapp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.SearchResultTeacherRecyclerAdapter;
import com.example.tutorsapp.models.SearchResultTeacherModel;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity implements View.OnClickListener {
    private Button searchBtn;
    private RecyclerView recyclerView;
    private List<SearchResultTeacherModel> searchResultTeacherModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        initView();
    }

    private void initView() {
        searchBtn = findViewById(R.id.searchBtn);
        recyclerView = findViewById(R.id.recyclerView);
        searchResultTeacherModelList = new ArrayList<>();

        searchResultTeacherModelList.add(new SearchResultTeacherModel("Adanan Malik", 5, "5 KM", "MBA", "25000 PKR", "I-8 Markaz"));
        searchResultTeacherModelList.add(new SearchResultTeacherModel("Adanan Malik", 5, "5 KM", "MBA", "25000 PKR", "I-8 Markaz"));
        searchResultTeacherModelList.add(new SearchResultTeacherModel("Adanan Malik", 5, "5 KM", "MBA", "25000 PKR", "I-8 Markaz"));
        searchResultTeacherModelList.add(new SearchResultTeacherModel("Adanan Malik", 5, "5 KM", "MBA", "25000 PKR", "I-8 Markaz"));

        SearchResultTeacherRecyclerAdapter adapter = new SearchResultTeacherRecyclerAdapter(searchResultTeacherModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }
}
