package com.example.tutorsapp.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.adapter.SelectedDatedRecyclerAdapter;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.helper.Persister;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.PreferedAreaModelRequest;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.network.BaseCallService;
import com.example.tutorsapp.ui.customview.CustomSpinnerAdapter;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.timessquare.CalendarPickerView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Response;

public class PreferredAreaToTeachActivity extends BaseActivity implements View.OnClickListener {
    private final int ADD_LOCATION_RESULT = 11;
    private final int POINT_OF_REFERENCE_RESULT = 13;
    private Calendar calender;
    private DatePickerDialog.OnDateSetListener date;
    private TextView updateYourAvailabilityStatusTv;
    private TextView datesTv, selecTimeTv;
    private TextView setPointOfReferenceTv;
    private FloatingActionButton addBtn;
    private CalendarPickerView calendar_view;
    private RecyclerView recyclerView;
    private Button saveChangesBtn;
    private int ids = 1;
    private List<String> datesSelected;
    private String dates = "";
    private FlexboxLayout addLocationsLL, pointOfRefLL;
    ImageView backIv;
    Spinner travelModeSpinner, araaSpinner;
    RadioGroup availableTimeRg;
    int profileId = 0;
    String radiusInKm, latlngString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_area_to_teach);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);// pretending keyboard from automatically opening
        initView();
        if (getIntent().getExtras() != null) {
            profileId = getIntent().getIntExtra("profileId", 0);
        }
    }

    private void initView() {
        pointOfRefLL = findViewById(R.id.pointOfRefLL);
        addLocationsLL = findViewById(R.id.addLocationsLL);
        setPointOfReferenceTv = findViewById(R.id.setPointOfReferenceTv);
        setPointOfReferenceTv.setOnClickListener(this);
        updateYourAvailabilityStatusTv = findViewById(R.id.updateYourAvailabilityStatusTv);
        updateYourAvailabilityStatusTv.setOnClickListener(this);
        calendar_view = (CalendarPickerView) findViewById(R.id.calendar_view);
        addBtn = findViewById(R.id.addBtn);
        datesTv = findViewById(R.id.datesTv);
        recyclerView = findViewById(R.id.recyclerView);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        saveChangesBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        datesSelected = new ArrayList<>();
        calender = Calendar.getInstance();
        calendar_view.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String d;
                int init = 1;
                for (int i = 0; i < 3; i++) {
                    init = date.toString().indexOf(" ", init + 1);
                }
                d = date.toString().substring(0, init);
                datesSelected.add(d);
                if (dates.isEmpty())
                    dates = dates + d;
                else
                    dates = dates + ", " + d;
            }

            @Override
            public void onDateUnselected(Date date) {

                datesSelected.remove(date.toString());
            }
        });

//getting current
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        Date today = new Date();
//add one year to calendar from todays date
        calendar_view.init(today, nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.MULTIPLE);

        final CheckBox time1Cb = findViewById(R.id.time1Cb);
        final CheckBox time2Cb = findViewById(R.id.time2Cb);
        final CheckBox time3Cb = findViewById(R.id.time3Cb);
        final CheckBox time4Cb = findViewById(R.id.time4Cb);
        final CheckBox time5Cb = findViewById(R.id.time5Cb);
        final CheckBox time6Cb = findViewById(R.id.time6Cb);
        selecTimeTv = findViewById(R.id.selecTimeTv);
        availableTimeRg = findViewById(R.id.availableTimeRg);
        availableTimeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.availableRb) {
//                    time1Cb.setVisibility(View.VISIBLE);
//                    time2Cb.setVisibility(View.VISIBLE);
//                    time3Cb.setVisibility(View.VISIBLE);
//                    time4Cb.setVisibility(View.VISIBLE);
//                    time5Cb.setVisibility(View.VISIBLE);
//                    time6Cb.setVisibility(View.VISIBLE);
//                    selecTimeTv.setVisibility(View.VISIBLE);
//                } else {
//                    time1Cb.setVisibility(View.GONE);
//                    time2Cb.setVisibility(View.GONE);
//                    time3Cb.setVisibility(View.GONE);
//                    time4Cb.setVisibility(View.GONE);
//                    time5Cb.setVisibility(View.GONE);
//                    time6Cb.setVisibility(View.GONE);
//                    selecTimeTv.setVisibility(View.GONE);
//                }
            }
        });
        backIv = findViewById(R.id.backIv);
        backIv.setOnClickListener(this);
        travelModeSpinner = findViewById(R.id.travelModeSpinner);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.travelModeArray))));
        travelModeSpinner.setAdapter(adapter);
        araaSpinner = findViewById(R.id.araaSpinner);
        CustomSpinnerAdapter areaSpinerAdapter = new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.areaArray))));
        araaSpinner.setAdapter(areaSpinerAdapter);
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        updateYourAvailabilityStatusTv.setText(sdf.format(calender.getTime()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_LOCATION_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                Address address = data.getParcelableExtra(Constants.datePassey);
                if (address != null)
                    addAddresses(address.getAddressLine(0));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        } else if (requestCode == POINT_OF_REFERENCE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra(Constants.datePassey);
                radiusInKm = data.getStringExtra("radiusKm");
                latlngString = data.getStringExtra("latlng");

                if (result != null && !result.isEmpty()) {
                    addPointOfRef(result);
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    private void addPointOfRef(String result) {

        RelativeLayout relativeLayout = slotRelativeLayout(false);
        TextView textView = getSlotTv(result);
        ImageView imageView = getCrossImg();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.addRule(RelativeLayout.START_OF, imageView.getId());
        textView.setLayoutParams(params);
        relativeLayout.addView(textView);
        relativeLayout.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointOfRefLL.removeView(relativeLayout);
            }
        });
        pointOfRefLL.addView(relativeLayout);
        pointOfRefLL.setVisibility(View.VISIBLE);
    }

    private void addAddresses(String addressLine) {
        RelativeLayout relativeLayout = slotRelativeLayout(true);
        TextView textView = getSlotTv(addressLine);
        ImageView imageView = getCrossImg();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) textView.getLayoutParams();
        params.addRule(RelativeLayout.START_OF, imageView.getId());
        textView.setLayoutParams(params);
        relativeLayout.addView(textView);
        relativeLayout.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLocationsLL.removeView(relativeLayout);
            }
        });
        addLocationsLL.addView(relativeLayout);
        addLocationsLL.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setPointOfReferenceTv:
                Intent i = new Intent(PreferredAreaToTeachActivity.this, SelectPointOfReferenceActivity.class);
                startActivityForResult(i, POINT_OF_REFERENCE_RESULT);
                break;
            case R.id.updateYourAvailabilityStatusTv:
                calendar_view.setVisibility(View.VISIBLE);
                addBtn.setVisibility(View.VISIBLE);
                break;
            case R.id.addBtn:
                calendar_view.setVisibility(View.GONE);
                addBtn.setVisibility(View.GONE);
//                datesTv.setVisibility(View.VISIBLE);
//                datesTv.setText(dates);
                recyclerView.setVisibility(View.VISIBLE);
                SelectedDatedRecyclerAdapter adapter = new SelectedDatedRecyclerAdapter(datesSelected);
                recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                recyclerView.setAdapter(adapter);
                break;
            case R.id.saveChangesBtn:
                if (validateInput()) {
                    addPreferedArea();

                } else
                    break;
//            case R.id.areaTv:
//                Intent locationIntent = new Intent(PreferredAreaToTeachActivity.this, AddLocationActivity.class);
//                startActivityForResult(locationIntent, ADD_LOCATION_RESULT);
//                break;
            case R.id.backIv:
                super.onBackPressed();
                break;
        }
    }

    private TextView getSlotTv(String name) {
        TextView textView = new TextView(PreferredAreaToTeachActivity.this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setId(ids);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(getResources().getDimensionPixelSize(R.dimen._15sdp), 0, 0, 0);
        textView.setText(name);
        textView.setTextColor(ContextCompat.getColor(PreferredAreaToTeachActivity.this, R.color.colorPrimary));
        ids++;
        return textView;
    }

    private RelativeLayout slotRelativeLayout(boolean forLocation) {
        RelativeLayout relativeLayout = new RelativeLayout(PreferredAreaToTeachActivity.this);
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                forLocation ? RelativeLayout.LayoutParams.MATCH_PARENT : getResources().getDimensionPixelSize(R.dimen._120sdp), RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.holo_brwn_circle));
        relativeParams.setMargins(getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp));
        relativeLayout.setLayoutParams(relativeParams);
        return relativeLayout;
    }

    private ImageView getCrossImg() {
        ImageView imageView = new ImageView(PreferredAreaToTeachActivity.this);
        imageView.setId(ids);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        params1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        params1.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen._10sdp), 0);
//                        params.addRule(RelativeLayout.START_OF,cancelImage.getId());
        imageView.setBackground(getResources().getDrawable(R.drawable.ic_close));
        imageView.setLayoutParams(params1);
        ids++;
        return imageView;
    }

    private boolean validateInput() {
        if (pointOfRefLL.getChildCount() == 0) {
            showToastBar("Please add your point of reference", this);
            return false;
        }
        return true;
    }


    private void addPreferedArea() {
        PreferedAreaModelRequest request = new PreferedAreaModelRequest();
        RadioButton radioButton = findViewById(availableTimeRg.getCheckedRadioButtonId());
        request.setAvailabilityStatus(radioButton.getText().toString().equals("Occupied") ? "0" : "1");
        request.setProfileID(profileId);
        request.setPrefferdArea("");
        request.setModeOfConvince((String) travelModeSpinner.getSelectedItem());
        request.setLatLong(latlngString);
        request.setPointOfReference(radiusInKm);// in Km
        APIManager.getInstance().addPreferedArea(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    TutorApp.userInfo.setProfileStatus("5");
                    Persister.setUser(PreferredAreaToTeachActivity.this, TutorApp.userInfo);
                    Intent intent = new Intent(PreferredAreaToTeachActivity.this, RegistrationFeeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    DialogHelper.showMessageDialog(PreferredAreaToTeachActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());

                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(PreferredAreaToTeachActivity.this, "Error", error);

            }
        }, request);
    }
}