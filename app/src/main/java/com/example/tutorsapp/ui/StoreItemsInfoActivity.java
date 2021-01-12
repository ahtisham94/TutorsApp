package com.example.tutorsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.SubjectTechAdapter;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.adapter.CustomSpinnerAdapter;
import com.example.tutorsapp.ui.dialogs.AddCategporyDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import retrofit2.Response;

public class StoreItemsInfoActivity extends BaseActivity implements View.OnClickListener, APIManager.CallbackGenric {
    private Button saveBtn;
    private ImageView backIv;
    private Spinner bookShopListSp;
    private SectionedRecyclerViewAdapter sectionedAdapter;
    private RecyclerView subjRecyclerView, oldCategoryRv, stationaryRv;
    private HashMap<String, List<LOVResponseModel>> teachCategoryHashmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_items_info);
        initView();
    }

    private void initView() {
        bookShopListSp = findViewById(R.id.bookShopListSp);
        saveBtn = findViewById(R.id.saveBtn);
        backIv = findViewById(R.id.backIv);
        subjRecyclerView = findViewById(R.id.subjectCategoryRecycler);
        oldCategoryRv = findViewById(R.id.oldCategoryRv);
        stationaryRv = findViewById(R.id.stationaryRv);

        bookShopListSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.book_shop_list)))));

        saveBtn.setOnClickListener(this);
        backIv.setOnClickListener(this);
        findViewById(R.id.whatCatDoYouEv).setOnClickListener(this);
        findViewById(R.id.contactDetailTv).setOnClickListener(this);
        findViewById(R.id.addressDatilsTv).setOnClickListener(this);
        findViewById(R.id.addmoreBtn).setOnClickListener(this);
    }

    private void showCategoryDialog(RecyclerView subjectRecyclerView) {
        ArrayList<LOVCategoryResponseModel> arrayList = getDummyData();
        new AddCategporyDialog(StoreItemsInfoActivity.this, new AddCategporyDialog.GetSubList() {
            @Override
            public void SubList(HashMap<String, List<LOVResponseModel>> listHashMap) {
                teachCategoryHashmap = listHashMap;
                sectionedAdapter = new SectionedRecyclerViewAdapter();
                for (Map.Entry<String, List<LOVResponseModel>> listEntry : listHashMap.entrySet()) {
                    sectionedAdapter.addSection(new SubjectTechAdapter(listEntry.getKey(), listEntry.getValue(), sectionedAdapter, new SubjectTechAdapter.ClickListener() {
                        @Override
                        public void onItemRootViewClicked(@NonNull SubjectTechAdapter section, int itemAdapterPosition) {
                            if (itemAdapterPosition != -1) {
                                try {
                                    sectionedAdapter.notifyItemRemoved(itemAdapterPosition);

                                } catch (Exception e) {
                                    Log.d("ex", "exception");
                                }


                            }

                        }
                    }));
                }
                subjectRecyclerView.setLayoutManager(new LinearLayoutManager(StoreItemsInfoActivity.this));
                subjectRecyclerView.setAdapter(sectionedAdapter);
                subjectRecyclerView.setVisibility(View.VISIBLE);

            }
        }, arrayList).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                //   if (validateInput()) {
                //     addUserInfo();
                Intent intent = new Intent(StoreItemsInfoActivity.this, BookShopDeliveryActivity.class);
                //         intent.putExtra("OwnerID", (int) ((GeneralResponse) response.body()).getData());
                startActivity(intent);
                //     }
                break;

            case R.id.backIv:
                super.onBackPressed();
                break;

            case R.id.whatCatDoYouEv:
                showCategoryDialog(subjRecyclerView);
                break;

            case R.id.contactDetailTv:
                showCategoryDialog(oldCategoryRv);
                break;

            case R.id.addressDatilsTv:
                showCategoryDialog(stationaryRv);
                break;

            case R.id.addmoreBtn:
                finish();
                startActivity(new Intent());
                break;
        }
    }

    private void validate() {

    }

    private void addInfoToServer() {

    }

    @Override
    public void onResult(boolean z, Response response) {

    }

    @Override
    public void onError(String error) {

    }

    private ArrayList<LOVCategoryResponseModel> getDummyData() {
        ArrayList<LOVCategoryResponseModel> list = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            LOVCategoryResponseModel lovCategoryResponseModel = new LOVCategoryResponseModel();
            lovCategoryResponseModel.setId("" + i);
            lovCategoryResponseModel.setName("Board no " + i);
            ArrayList<LOVResponseModel> modelArrayList = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                LOVResponseModel lovResponseModel = new LOVResponseModel();
                lovResponseModel.setId("" + i +  "" + j);
                lovResponseModel.setName("Text book board no" + j);
                modelArrayList.add(lovResponseModel);
            }
            lovCategoryResponseModel.setCatList(modelArrayList);
            list.add(lovCategoryResponseModel);
        }
        return list;
    }
}
