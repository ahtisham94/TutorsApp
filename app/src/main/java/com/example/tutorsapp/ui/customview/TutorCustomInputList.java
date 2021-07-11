package com.example.tutorsapp.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.tutorsapp.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.Objects;

public class TutorCustomInputList extends ConstraintLayout implements View.OnClickListener {
    LayoutInflater mInflater;
    TextView hintLbl;
    EditText inputEd;
    TypedArray typedArray;
    Resources resources;
    Button addBtn;
    FlexboxLayout flexboxLayout;
    private int viewIds = 1;

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
        addBtn.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addBtn) {
            addItemInList();
        }
    }

    private void addItemInList() {
        if (!inputEd.getText().toString().isEmpty()) {
            RelativeLayout timeSlotLayout = slotRelativeLayout();
            TextView timeSlotTv = getSlotTv(inputEd.getText().toString());
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) timeSlotTv.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, timeSlotLayout.getId());
            ImageView cancelImage = getCrossImg();
            params.addRule(RelativeLayout.START_OF, cancelImage.getId());
            timeSlotTv.setLayoutParams(params);
            timeSlotLayout.addView(timeSlotTv);
            timeSlotLayout.addView(cancelImage);
            flexboxLayout.addView(timeSlotLayout);
            cancelImage.setOnClickListener(view -> {
                flexboxLayout.removeView(timeSlotLayout);
            });
        }
    }


    private RelativeLayout slotRelativeLayout() {
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(viewIds);
        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                getResources().getDimensionPixelSize(R.dimen._120sdp), RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.holo_brwn_circle));
        relativeParams.setMargins(getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp),
                getResources().getDimensionPixelSize(R.dimen._5sdp));
        relativeLayout.setLayoutParams(relativeParams);
        viewIds++;
        return relativeLayout;
    }


    private TextView getSlotTv(String name) {
        TextView textView = new TextView(getContext());
        textView.setId(viewIds);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setPadding(getResources().getDimensionPixelSize(R.dimen._15sdp), 0, 0, 0);
        textView.setText(name);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        viewIds++;
        return textView;
    }


    private ImageView getCrossImg() {
        ImageView imageView = new ImageView(getContext());
        imageView.setId(viewIds);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        params1.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        params1.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen._10sdp), 0);
//                        params.addRule(RelativeLayout.START_OF,cancelImage.getId());
        imageView.setBackground(getResources().getDrawable(R.drawable.ic_close));
        imageView.setLayoutParams(params1);
        viewIds++;
        return imageView;
    }

    public String getListData() {
        StringBuilder data = new StringBuilder();
        if (flexboxLayout.getChildCount() > 1) {
            for (int i = 0; i < flexboxLayout.getChildCount(); i++) {
                RelativeLayout relativeLayout = (RelativeLayout) flexboxLayout.getChildAt(0);
                TextView r = (TextView) relativeLayout.getChildAt(0);
                if (data.length() == 0) {
                    data = new StringBuilder(r.getText().toString());
                } else {
                    data.append(",").append(r.getText().toString());

                }
            }

        }
        return data.toString();
    }
}
