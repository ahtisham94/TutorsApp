package com.example.tutorsapp.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.tutorsapp.R;
import com.example.tutorsapp.TutorApp;
import com.example.tutorsapp.adapter.GenericCustomSpinnerAdapter;
import com.example.tutorsapp.enumerationss.LOVsType;
import com.example.tutorsapp.enumerationss.TeacherTypeEnum;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.helper.DialogHelper;
import com.example.tutorsapp.models.GeneralResponse;
import com.example.tutorsapp.models.LOVResponseModel;
import com.example.tutorsapp.network.APIManager;
import com.example.tutorsapp.adapter.CustomSpinnerAdapter;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class EditBookStoreInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final int PICK_FROM_CAMERA_FRONT = 1;
    private static final int PICK_FROM_GALLARY_FRONT = 2;
    private static final int PICK_FROM_CAMERA_BACK = 3;
    private static final int PICK_FROM_GALLARY_BACK = 4;
    private static final int PICK_FROM_CAMERA_PROFILE = 15;
    private static final int PICK_FROM_GALLARY_PROFILE = 6;

    Spinner genderSp, citySp;
    private int permissionAskedFor; //0 for front, 1 for back
    EditText fullNameEd, cnicEv;
    private EditText contactDetailsEt, emailAddress, firstNameEd, lastNameEd, currentAddressEd, permanentAddressEd;
    private TextView cnicFront;
    private TextView cnicBack;
    private Button saveBtn;
    private CircularImageView profile_image;
    ImageView backIv, editProfileImageIv;
    private boolean isSelectedBack = false, isProfilePicSelected = false, isSelectedFront = false;
    File profileFile, backCnicFile, frontCnicFile;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book_store_info);
        initViews();
    }

    private void initViews() {
        fullNameEd = findViewById(R.id.fullNameEd);
        cnicEv = findViewById(R.id.cnicEv);
        genderSp = findViewById(R.id.genderSp);
        genderSp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.gender)))));
        citySp = findViewById(R.id.citySp);
        contactDetailsEt = findViewById(R.id.contactDetailsEt);
        currentAddressEd = findViewById(R.id.currentAddressEt);
        permanentAddressEd = findViewById(R.id.parmanentAddressEt);
        cnicFront = findViewById(R.id.cnicFront);
        saveBtn = findViewById(R.id.saveBtn);
        backIv = findViewById(R.id.backIv);
        profile_image = findViewById(R.id.profile_image);
        editProfileImageIv = findViewById(R.id.editProfileImageIv);
        cnicFront = findViewById(R.id.cnicFront);
        cnicBack = findViewById(R.id.cnicBack);

        saveBtn.setOnClickListener(this);
        cnicFront.setOnClickListener(this);
        cnicBack.setOnClickListener(this);
        editProfileImageIv.setOnClickListener(this);
        backIv.setOnClickListener(this);

        //TODO remove this line
        citySp.setAdapter(new CustomSpinnerAdapter(this, R.id.spinner_item_tv, R.id.spinner_item_tv, new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.city)))));

        APIManager.getInstance().getLovs(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    ArrayList<LOVResponseModel> lovResponseModelArrayList = (ArrayList<LOVResponseModel>) ((GeneralResponse) response.body()).getData();
                    citySp.setAdapter(new GenericCustomSpinnerAdapter(EditBookStoreInfoActivity.this, R.id.spinner_item_tv, R.id.spinner_item_tv, lovResponseModelArrayList));
                } else {
                    DialogHelper.showMessageDialog(EditBookStoreInfoActivity.this, "Error", response.message());
                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(EditBookStoreInfoActivity.this, "Error", error);

            }
        }, LOVsType.CITES_LOVS.lovsRequestModel);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //if permission granted
                showAlertDialog(permissionAskedFor);
            }
        }
    }

    private void showRemovePhotoAlertDialog(final int cnicSide) { // 0 for front, 1 for back
        AlertDialog alertDialog = new AlertDialog.Builder(EditBookStoreInfoActivity.this).create();
        alertDialog.setTitle("Remove Photo");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Are you sure you want to remove the photo?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (cnicSide == 0) {
                            cnicFront.setText(getResources().getText(R.string.upload_cnic_front));
                            cnicFront.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cnic_icon, 0);
                            isSelectedFront = false;
                        } else {
                            cnicBack.setText(getResources().getText(R.string.upload_cnic_back));
                            cnicBack.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.cnic_icon, 0);
                            isSelectedBack = false;
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

    private void showAlertDialog(final int cnicSide) { // 0 for front, 1 for back
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
                    if (cnicSide == 0) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_FROM_GALLARY_FRONT);
                    } else if (cnicSide == 1) {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_FROM_GALLARY_BACK);
                    } else {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, PICK_FROM_GALLARY_PROFILE);
                    }
                    dialog.dismiss();

                }
            });

        if (captureTv != null)
            captureTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cnicSide == 0) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_FROM_CAMERA_FRONT);
                    } else if (cnicSide == 1) {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_FROM_CAMERA_BACK);
                    } else {
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, PICK_FROM_CAMERA_PROFILE);
                    }
                }
            });

        dialog.show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_FROM_CAMERA_FRONT:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    isSelectedFront = true;
                    cnicFront.setText(Constants.CNIC_FRONT_SUCCESS_MESSAGE);
                    cnicFront.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap scaled = Constants.getScaledBitmap((Bitmap) extras.get("data"));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        frontCnicFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);

                    } else {
                        Toast.makeText(this, "Image cannot be updated", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case PICK_FROM_GALLARY_FRONT:
                if (resultCode == Activity.RESULT_OK) {
                    cnicFront.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    dialog.dismiss();
                    isSelectedFront = true;

                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap scaled = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
//                        cnicFrontBase64String = Constants.stringBase64Img(scaled);
                        frontCnicFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(EditBookStoreInfoActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }

                }
                break;
            case PICK_FROM_GALLARY_BACK:
                if (resultCode == Activity.RESULT_OK) {
                    cnicBack.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    dialog.dismiss();
                    //pick image from gallery
                    cnicBack.setText(Constants.CNIC_BACK_SUCCESS_MESSAGE);
                    isSelectedBack = true;  // to close the dialog box if image is selected

                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap scaled = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
//                        cnicBackBase64String = Constants.stringBase64Img(scaled);
                        backCnicFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(EditBookStoreInfoActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case PICK_FROM_CAMERA_BACK:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    isSelectedBack = true;  // to close the dialog box if image is selected
                    cnicBack.setText(Constants.CNIC_BACK_SUCCESS_MESSAGE);
                    cnicBack.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap scaled = Constants.getScaledBitmap((Bitmap) extras.get("data"));
//                        cnicBackBase64String = Constants.stringBase64Img(scaled);
                        backCnicFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);
                    } else {
                        Toast.makeText(this, "Image cannot be updated", Toast.LENGTH_LONG).show();
                    }

                }

                break;
            case PICK_FROM_GALLARY_PROFILE:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap scaled = Constants.getScaledBitmap(BitmapFactory.decodeStream(imageStream));
                        profile_image.setImageBitmap(scaled);
                        isProfilePicSelected = true;
//                        profileBase64String = Constants.stringBase64Img(scaled);
                        profileFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(EditBookStoreInfoActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                    }
                    //pick image from gallery  cnicBack.setText(Constants.CNIC_PROFILE_SUCCESS_MESSAGE);
                }
                break;
            case PICK_FROM_CAMERA_PROFILE:
                if (resultCode == Activity.RESULT_OK) {
                    dialog.dismiss();
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap scaled = Constants.getScaledBitmap((Bitmap) extras.get("data"));
                        profile_image.setImageBitmap(scaled);
                        isProfilePicSelected = true;
//                        profileBase64String = Constants.stringBase64Img(scaled);
                        profileFile = Constants.getScaledFile(scaled, EditBookStoreInfoActivity.this);
                    } else {
                        Toast.makeText(this, "Image cannot be updated", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.saveBtn:
                //if (validateInput()) {
                    Intent intent = new Intent(EditBookStoreInfoActivity.this, BookShopInformationActivity.class);
                    startActivity(intent);
                    //   addUserInfo();
         //       }

                break;
            case R.id.backIv:
                EditBookStoreInfoActivity.super.onBackPressed();
                break;
            case R.id.editProfileImageIv:
                if (ContextCompat.checkSelfPermission(EditBookStoreInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissionAskedFor = 1;
                    ActivityCompat.requestPermissions(EditBookStoreInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    showAlertDialog(2);
                }
                break;
            case R.id.cnicBack:
                if (ContextCompat.checkSelfPermission(EditBookStoreInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissionAskedFor = 1;
                    ActivityCompat.requestPermissions(EditBookStoreInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    if (!isSelectedBack)
                        showAlertDialog(1);
                    else
                        showRemovePhotoAlertDialog(1);
                }
                break;

            case R.id.cnicFront:
                if (ContextCompat.checkSelfPermission(EditBookStoreInfoActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissionAskedFor = 0;
                    ActivityCompat.requestPermissions(EditBookStoreInfoActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                } else {
                    if (!isSelectedFront)
                        showAlertDialog(0);
                    else
                        showRemovePhotoAlertDialog(0);
                }
                break;


        }
    }

    private boolean validateInput() {
        if (!isProfilePicSelected) {
            showToastBar("Please select and take your profile picture", this);
            return false;
        } else if (firstNameEd.getText() == null || firstNameEd.getText().toString().isEmpty()) {
            firstNameEd.setError("Please enter first name");
            firstNameEd.requestFocus();
            return false;
        } else if (lastNameEd.getText() == null || lastNameEd.getText().toString().isEmpty()) {
            lastNameEd.setError("Please enter last name");
            lastNameEd.requestFocus();
            return false;
        } else if (contactDetailsEt.getText().toString().isEmpty()) {
            contactDetailsEt.setError("Please enter contact number");
            contactDetailsEt.requestFocus();
            return false;
        } else if (cnicEv.getText().toString().isEmpty()) { // TODO :regex left
            cnicEv.setError("Please enter cnic number");
            cnicEv.requestFocus();
            return false;
        } else if (contactDetailsEt.length() > 11 || !Constants.phoneRegex(contactDetailsEt.getText().toString())) {
            contactDetailsEt.setError("Please enter valid contact number");
            contactDetailsEt.requestFocus();
            return false;
        } else if (emailAddress.getText() == null || emailAddress.getText().toString().isEmpty() || !Constants.emailValidator(emailAddress.getText().toString())) {
            emailAddress.setError("Please enter valid email address");
            emailAddress.requestFocus();
            return false;
        } else if (currentAddressEd.getText() == null || currentAddressEd.getText().toString().isEmpty()) {
            currentAddressEd.setError("Please enter current address");
            currentAddressEd.requestFocus();
            return false;
        } else if ((Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE && permanentAddressEd.getText().toString().length() < 13) || (Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER || Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER) && (permanentAddressEd.getText() == null || permanentAddressEd.getText().toString().isEmpty())) {
            permanentAddressEd.setError(Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE ? "Please enter valid CNIC number" : "Please enter parmanent address");
            permanentAddressEd.requestFocus();
            return false;
        } else if ((Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER || Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER) && !isSelectedFront) {
            showToastBar("Please add front cnic picture", this);
            return false;
        } else if ((Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER || Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER) && !isSelectedBack) {
            showToastBar("Please add back cnic picture", this);
            return false;
        } else {
            firstNameEd.setError(null);
            lastNameEd.setError(null);
            currentAddressEd.setError(null);
            emailAddress.setError(null);
            permanentAddressEd.setError(null);
            contactDetailsEt.setError(null);
            return true;
        }

    }


    private void addUserInfo() {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("ProfilePic", profileFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), profileFile));
        builder.addFormDataPart("MSISDN", TutorApp.userInfo.getMsisdn());
        builder.addFormDataPart("SessionId", TutorApp.userInfo.getSessionId());
        builder.addFormDataPart("FName", firstNameEd.getText().toString());
        builder.addFormDataPart("LName", lastNameEd.getText().toString());
        //    builder.addFormDataPart("Dob", dateOfBirth);
        builder.addFormDataPart("Gender", (String) genderSp.getSelectedItem());
        builder.addFormDataPart("Email", emailAddress.getText().toString());
        builder.addFormDataPart("PermanentAddress", (Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER || Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER) ? permanentAddressEd.getText().toString() : currentAddressEd.getText().toString());
        builder.addFormDataPart("CurrentAddress", currentAddressEd.getText().toString());
        builder.addFormDataPart("ProfilePicPath", profileFile.getAbsolutePath());
        builder.addFormDataPart("CityID", ((LOVResponseModel) citySp.getSelectedItem()).getId());
        if ((Constants.teacherTye == TeacherTypeEnum.PROFESSIONAL_TEACHER || Constants.teacherTye == TeacherTypeEnum.QURAN_TEACHER)) {
            builder.addFormDataPart("CNICBack", backCnicFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), backCnicFile));
            builder.addFormDataPart("CNICFront", frontCnicFile.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), frontCnicFile));
            builder.addFormDataPart("ContactNo", contactDetailsEt.getText().toString());
            builder.addFormDataPart("CNICBackPath", backCnicFile.getAbsolutePath());
            builder.addFormDataPart("CNICFrontPath", frontCnicFile.getAbsolutePath());
            builder.addFormDataPart("TeacherType", Constants.teacherTye.teacherType.type + "");
        } else if (Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE) {
            //permanentAddressEd is work as CNIC in adacemy owner info
            builder.addFormDataPart("Cnic", permanentAddressEd.getText().toString());
            builder.addFormDataPart("ChannelID", "0");
            builder.addFormDataPart("PrimaryContactNo", contactDetailsEt.getText().toString());
            builder.addFormDataPart("SecondaryContactNo", contactDetailsEt.getText().toString());

        }
        MultipartBody requestBody = builder.build();
        APIManager.getInstance().addTeacherBasicInfo(new APIManager.CallbackGenric() {
            @Override
            public void onResult(boolean z, Response response) {
                if (((GeneralResponse) response.body()).getIsSuccess()) {
                    if (Constants.teacherTye == TeacherTypeEnum.ACADEMIC_INSTITUTE) {
                        Intent intent = new Intent(EditBookStoreInfoActivity.this, AcademyInformationActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("OwnerID", (int) ((GeneralResponse) response.body()).getData());
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(EditBookStoreInfoActivity.this, EducationalDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("profileId", (int) ((GeneralResponse) response.body()).getData());
                        startActivity(intent);
                    }

                } else {
                    DialogHelper.showMessageDialog(EditBookStoreInfoActivity.this, "Error", ((GeneralResponse) response.body()).getMessage());

                }
            }

            @Override
            public void onError(String error) {
                DialogHelper.showMessageDialog(EditBookStoreInfoActivity.this, "Error", error);

            }
        }, requestBody);

    }
}