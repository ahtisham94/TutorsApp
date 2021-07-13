package com.example.tutorsapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;

import com.example.tutorsapp.enumerationss.TeacherTypeEnum;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Constants {
    public static final String INSTITUTION_TEXT_ERROR = "Please add instutution name";
    public static final String FINAL_DEGREE_IMAGE_UPLOADED = "Final Degree Image Uploaded";
    public static final String CNIC_FRONT_SUCCESS_MESSAGE = "CNIC Front uploaded successfully";
    public static final String SHOP_LOGO_SUCCESS = "Shop logo uploaded successfully";
    public static final String SHOP_PICTURE_SUCCESS = "Shop picture uploaded successfully";
    public static final String CNIC_BACK_SUCCESS_MESSAGE = "CNIC Back uploaded successfully";
    public static final String datePassey = "datePassey";
    public static final String dataPrefsKey = "dataPrefskey";
    public static final int ADD_LOCATION_RESULT = 1231;
    public static final int GET_PICK_GALARY = 1013;
    public static final int TAKE_PIC_CAMERA = 1234;
    public static final int AVAILABLE_JOBS = 1234;
    public static final int APPLIED_JOBS = 1235;
    public static final int JOB_CONFIATION = 123785;
    public static final int CONFRIM_JOBS = 35564;
    public static final int VIEW_JOB = 1236;
    public static final String timeFormate = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static TeacherTypeEnum teacherTye;
    public static TeacherTypeEnum ImagePickerType;
    public static String userLoginKey = "loginResponse";
    public static String firstLaunch = "firstLaunch";

    public static boolean emailValidator(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public static boolean phoneRegex(String s) {
        Pattern p = Pattern.compile("^03");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean cnicRegix(String s) {
        Pattern p = Pattern.compile("^03");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static String stringBase64Img(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static File getScaledFile(Bitmap bitmap, Context context) {
        try {
            File f = new File(context.getCacheDir(), "image.jpg");
            f.createNewFile();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outputStream);
            byte[] bitmapdata = outputStream.toByteArray();
            FileOutputStream fos = null;
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return f;

//            return resizeBitmap;
        } catch (Exception e) {
            return null;
        }

    }


    public static Bitmap getScaledBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        Log.i("Oldwidth", width + "");
        int height = bitmap.getHeight();
        Log.i("Oldheight", height + "");
        Matrix matrix = new Matrix();
        float scaleWidth;
        float scaleHeight;
        scaleWidth = ((float) 400) / width;
        scaleHeight = ((float) 400) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizeBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        resizeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return resizeBitmap;
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    public static RequestBody toRequestBodyForFile(File value) {
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), value);
        return body;
    }


}
