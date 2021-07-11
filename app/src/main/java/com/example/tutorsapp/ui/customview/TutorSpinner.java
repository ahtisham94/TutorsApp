package com.example.tutorsapp.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tutorsapp.R;

import java.util.Objects;

public class TutorSpinner extends ConstraintLayout {

    LayoutInflater mInflater;
    TextView hintLbl;
    AppCompatSpinner spinner;
    TypedArray typedArray;
    Resources resources;
    public TutorSpinner(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        initViews();
    }

    public TutorSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);

    }

    public TutorSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);

    }


    private void initViews() {
        View v = mInflater.inflate(R.layout.tutor_spinner_layout, this);
        hintLbl = v.findViewById(R.id.titleTv);
        spinner = v.findViewById(R.id.editText);
        bindViews();
    }

    private void bindViews() {
    }

    private void parseAttributes(AttributeSet attrs) {
        typedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.TutorEditText, 0, 0);
        resources = getContext().getResources();
        try {
            hintLbl.setText(typedArray.getString(R.styleable.TutorEditText_titletext));
        } catch (Exception e) {
            Log.e("ex", Objects.requireNonNull(e.getMessage()));
        }

    }

}
