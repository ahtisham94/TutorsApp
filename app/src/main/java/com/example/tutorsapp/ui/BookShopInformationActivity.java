package com.example.tutorsapp.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.adapter.CustomSpinnerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static com.example.tutorsapp.helper.Constants.ADD_LOCATION_RESULT;

public class BookShopInformationActivity extends BaseActivity implements View.OnClickListener {
    private static final int PICK_SHOP_LOGO_CAMERA = 1;
    private static final int PICK_SHOP_LOGO_GALLERY = 2;
    private static final int PICK_SHOP_PICTURE_CAMERA = 3;
    private static final int PICK_SHOP_PICTURE_GALLERY = 4;

    private boolean isSelectedShopPicture = false;
    private boolean isSelectedLogo = false;

    private AlertDialog dialog;

    private Spinner dealSp;
    private EditText shopNameEt, contactDetailsEt;
    private TextView shopLogo;
    private TextView shopPic;
    private TextView fromTv, toTv;
    private TextView shopLocation;
    private Button saveBtn;
    private Button addMoreBtn;
    private ImageView backIv;
    File logoFile, pictureFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_shop_information);
        initViews();
    }

    private void initViews() {
        dealSp = findViewById(R.id.dealSp);
        shopNameEt = findViewById(R.id.shopNameEt);
        contactDetailsEt = findViewById(R.id.contactDetailsEt);
        shopLogo = findViewById(R.id.shopLogo);
        shopPic = findViewById(R.id.shopPic);
        fromTv = findViewById(R.id.fromTv);
        toTv = findViewById(R.id.toTv);
        backIv = findViewById(R.id.backIv);
        shopLocation = findViewById(R.id.shopLocation);
        saveBtn = findViewById(R.id.saveBtn);
        addMoreBtn = findViewById(R.id.addMoreBtn);

        dealSp = findViewById(R.id.dealSp);
        dealSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.deal)))));

        shopLogo.setOnClickListener(this);
        shopPic.setOnClickListener(this);
        shopLocation.setOnClickListener(this);
        fromTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
        toTv.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        addMoreBtn.setOnClickListener(this);
    }

    private void showAlertDialog(final int shopimage) { // 0 for front, 1 for back
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.li_capture_image_alert, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(true);

        dialog = alertDialogBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        TextView galleryTv = view.findViewById(R.id.galleryTv);
        TextView captureTv = view.findViewById(R.id.captureTv);
        if (galleryTv != null)
            galleryTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (shopimage == 0) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_SHOP_LOGO_GALLERY);
                    } else if (shopimage == 1) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_SHOP_PICTURE_GALLERY);
                    }

                    dialog.dismiss();
                }
            });

        if (captureTv != null)
            captureTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (shopimage == 0) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_SHOP_LOGO_CAMERA);
                    } else if (shopimage == 1) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_SHOP_LOGO_CAMERA);
                    }
                }
            });

        dialog.show();
    }

    private void showRemovePhotoAlertDialog(final int shopImageNum) { // 0 for logo, 1 for shop pic
        AlertDialog alertDialog = new AlertDialog.Builder(BookShopInformationActivity.this).create();
        alertDialog.setTitle("Remove Photo");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Are you sure you want to remove the photo?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (shopImageNum == 0) {
                            shopLogo.setText(getResources().getText(R.string.logo_of_shop));
                            shopLogo.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_add_photo, 0);
                            isSelectedLogo = false;
                        } else {
                            shopPic.setText(getResources().getText(R.string.picture_of_shop));
                            shopPic.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_add_photo, 0);
                            isSelectedShopPicture = false;
                        }
                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private boolean validateInput() {
        if (shopNameEt.getText() == null || shopNameEt.getText().toString().isEmpty()) {
            shopNameEt.setError("Please enter shop name");
            shopNameEt.requestFocus();
            return false;
        } else if (contactDetailsEt.getText() == null || contactDetailsEt.getText().toString().isEmpty()) {
            contactDetailsEt.setError("Please enter contact details");
            contactDetailsEt.requestFocus();
            return false;
        } else if (toTv.getText() == null || toTv.getText().toString().equalsIgnoreCase("to")) {
            return false;
        } else if (fromTv.getText() == null || fromTv.getText().toString().equalsIgnoreCase("from")) {
            return false;
        } else if (!isSelectedLogo) {
            showToastBar("Please select logo of shop", this);
            return false;
        } else if (!isSelectedShopPicture) {
            showToastBar("Please select picture of shop", this);
            return false;
        } else {
            shopNameEt.setError(null);
            contactDetailsEt.setError(null);
            return true;
        }
    }

    String latLong;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ADD_LOCATION_RESULT:
                if (resultCode == Activity.RESULT_OK) {
                    Address address = data.getParcelableExtra(Constants.datePassey);
                    if (address != null) {
                        shopLocation.setText(address.getAddressLine(0));
                        latLong = data.getStringExtra("latlong");
                    }
                }
                break;

            case PICK_SHOP_LOGO_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    isSelectedLogo = true;
                    shopLogo.setText(Constants.SHOP_LOGO_SUCCESS);
                    shopLogo.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap scaled = Constants.getScaledBitmap((Bitmap) extras.get("data"));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        logoFile = Constants.getScaledFile(scaled, BookShopInformationActivity.this);

                    } else {
                        Toast.makeText(this, "Image cannot be updated", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case PICK_SHOP_LOGO_GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    shopLogo.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    dialog.dismiss();
                    isSelectedLogo = true;
                    shopLogo.setText(Constants.SHOP_LOGO_SUCCESS);

                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap scaled = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        logoFile = Constants.getScaledFile(scaled, BookShopInformationActivity.this);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(BookShopInformationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case PICK_SHOP_PICTURE_CAMERA:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    isSelectedShopPicture = true;
                    shopPic.setText(Constants.SHOP_PICTURE_SUCCESS);
                    shopPic.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap scaled = Constants.getScaledBitmap((Bitmap) extras.get("data"));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        pictureFile = Constants.getScaledFile(scaled, BookShopInformationActivity.this);

                    } else {
                        Toast.makeText(this, "Image cannot be updated", Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case PICK_SHOP_PICTURE_GALLERY:
                if (resultCode == Activity.RESULT_OK) {
                    shopPic.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    dialog.dismiss();
                    isSelectedShopPicture = true;
                    shopPic.setText(Constants.SHOP_PICTURE_SUCCESS);

                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap scaled = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        pictureFile = Constants.getScaledFile(scaled, BookShopInformationActivity.this);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(BookShopInformationActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.shopLogo: // 1 for logo
                if (ContextCompat.checkSelfPermission(BookShopInformationActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(BookShopInformationActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    if (!isSelectedLogo)
                        showAlertDialog(0);
                    else
                        showRemovePhotoAlertDialog(0);
                }
                break;

            case R.id.shopPic:  // 0 for picture
                if (ContextCompat.checkSelfPermission(BookShopInformationActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(BookShopInformationActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    if (!isSelectedShopPicture)
                        showAlertDialog(1);
                    else
                        showRemovePhotoAlertDialog(1);
                }
                break;

            case R.id.shopLocation:
                Intent intentt = new Intent(BookShopInformationActivity.this, AddLocationActivity.class);
                startActivityForResult(intentt, ADD_LOCATION_RESULT);
                break;

            case R.id.saveBtn:
                //   if (validateInput()) {
                //     addUserInfo();
                Intent intent = new Intent(BookShopInformationActivity.this, StoreItemsInfoActivity.class);
                //         intent.putExtra("OwnerID", (int) ((GeneralResponse) response.body()).getData());
                startActivity(intent);
                //     }
                break;

            case R.id.fromTv:
                showTime(0);
                break;

            case R.id.toTv:
                showTime(1);
                break;

            case R.id.backIv:
                super.onBackPressed();
                break;

            case R.id.addMoreBtn:
                //   if (validateInput()) {
                //     addUserInfo();
                finish();
                startActivity(getIntent());
                //}
                break;
        }
    }

    private void showTime(int fromOrTo)  // 0 for from 1 for to
    {
        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        final int minute = myCalender.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);
                    String time = "am";
                    if (hourOfDay > 12) {  // converting in 12 hour format
                        hourOfDay = hourOfDay - 12;
                        time = "pm";
                    }
                    if (fromOrTo == 0)
                        if (minute < 10)
                            fromTv.setText("" + hourOfDay + " : " + "0" + minute + " " + time);
                        else
                            fromTv.setText("" + hourOfDay + " : " + "" + minute + " " + time);
                    if (fromOrTo == 1)
                        if (minute < 10)
                            toTv.setText("" + hourOfDay + " : " + "0" + minute + " " + time);
                        else
                            toTv.setText("" + hourOfDay + " : " + "" + minute + " " + time);

                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, false);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }


}