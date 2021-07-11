package com.example.tutorsapp.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tutorsapp.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Objects;

public class TutorCustomInputList extends ConstraintLayout {
    LayoutInflater mInflater;
    TextView hintLbl;
    EditText inputEd;
    TypedArray typedArray;
    Resources resources;
    Button addBtn;
    FlexboxLayout flexboxLayout;
    public TutorCustomInputList(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        initViews();
    }

    public TutorCustomInputList(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);
    }

    public TutorCustomInputList(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);
    }

    private void initViews() {
        View v = mInflater.inflate(R.layout.custom_input_list_layout, this);
        hintLbl = v.findViewById(R.id.titleTv);
        inputEd = v.findViewById(R.id.inputEd);
        addBtn = v.findViewById(R.id.addBtn);
        flexboxLayout = v.findViewById(R.id.flexboxLayout);
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
