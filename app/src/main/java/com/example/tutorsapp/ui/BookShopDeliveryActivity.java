package com.example.tutorsapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.AccountDetails;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class BookShopDeliveryActivity extends BaseActivity implements View.OnClickListener {
    private Spinner bookShopListSp, minTimeWithinCitySp, maxTimeWithinCitySp, minTimeSp, maxTimeSp;
    private ImageView backIv;
    private Button saveBtn, addmoreBtn;
    private RadioGroup homeDeliveryRg;
    private ConstraintLayout deliveryDetailCl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop_delivery);
        initView();
    }

    private void initView() {
        bookShopListSp = findViewById(R.id.bookShopListSp);
        minTimeWithinCitySp = findViewById(R.id.minTimeWithinCitySp);
        maxTimeWithinCitySp = findViewById(R.id.maxTimeWithinCitySp);
        minTimeSp = findViewById(R.id.minTimeSp);
        maxTimeSp = findViewById(R.id.maxTimeSp);
        backIv = findViewById(R.id.backIv);
        saveBtn = findViewById(R.id.saveBtn);
        addmoreBtn = findViewById(R.id.addmoreBtn);
        deliveryDetailCl = findViewById(R.id.deliveryDetailCl);

        bookShopListSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.book_shop_list)))));
        minTimeWithinCitySp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.min_time_withincity_spinner)))));
        maxTimeWithinCitySp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.max_time_withincity_spinner)))));
        minTimeSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.min_time_withincity_spinner)))));
        maxTimeSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.max_time_withincity_spinner)))));

        backIv.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        addmoreBtn.setOnClickListener(this);
        homeDeliveryRg = findViewById(R.id.homeDeliveryRg);

        homeDeliveryRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.yesRb:
                        deliveryDetailCl.setVisibility(View.VISIBLE);
                        break;
                    case R.id.noRb:
                        deliveryDetailCl.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                //   if (validateInput()) {
                //     addUserInfo();
                Intent intent = new Intent(BookShopDeliveryActivity.this, AccountDetailsActivity.class);
                //         intent.putExtra("OwnerID", (int) ((GeneralResponse) response.body()).getData());
                startActivity(intent);
                //     }
                break;
            case R.id.backIv:
                super.onBackPressed();
                break;
            case R.id.addmoreBtn:
                //   if (validateInput()) {
                //     addUserInfo();
                finish();
                startActivity(getIntent());
                //}
                break;
        }
    }
}