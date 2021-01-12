package com.example.tutorsapp.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.tutorsapp.R;

import java.util.zip.Inflater;

public class TimeLineCustomView extends LinearLayout {
    TextView twentyPercentTv, fourtyPercentTv, sixtyPercentTv, eightyPercentTv, hundredPercentTv;
    int fillCircleCount = 0, fillCircleTxtColor = 0;
    Drawable fillCircleBg;
    TypedArray typedArray;
    Resources resources;
    LayoutInflater mInflater;

    public TimeLineCustomView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        intiViews();
    }

    public TimeLineCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        parseAttributes(attrs);
        intiViews();

    }

    public TimeLineCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        parseAttributes(attrs);
        intiViews();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TimeLineCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mInflater = LayoutInflater.from(context);
        parseAttributes(attrs);
        intiViews();

    }

    private void intiViews() {
        View rootLayout = mInflater.inflate(R.layout.timeline_view_layout, this, true);
        twentyPercentTv = rootLayout.findViewById(R.id.twentyPercent);
        fourtyPercentTv = rootLayout.findViewById(R.id.foutyPercent);
        sixtyPercentTv = rootLayout.findViewById(R.id.sixtyPercent);
        eightyPercentTv = rootLayout.findViewById(R.id.eightyPercent);
        hundredPercentTv = rootLayout.findViewById(R.id.hundredPercent);
        bindView();
    }

    private void bindView() {
        if (fillCircleCount == 1) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (fillCircleCount == 2) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (fillCircleCount == 3) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (fillCircleCount == 4) {

            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
            eightyPercentTv.setBackground(fillCircleBg);
            eightyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (fillCircleCount == 5) {


            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
            eightyPercentTv.setBackground(fillCircleBg);
            eightyPercentTv.setTextColor(fillCircleTxtColor);
            hundredPercentTv.setBackground(fillCircleBg);
            hundredPercentTv.setTextColor(fillCircleTxtColor);
        } else {

            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
        }
    }

    private void parseAttributes(AttributeSet attrs) {
        typedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.TimeLineCustomView, 0, 0);
        resources = getContext().getResources();
        try {
            fillCircleBg = typedArray.getDrawable(R.styleable.TimeLineCustomView_fill_circle_bg);
            fillCircleCount = typedArray.getInt(R.styleable.TimeLineCustomView_fill_percent, 1);
            fillCircleTxtColor = typedArray.getColor(R.styleable.TimeLineCustomView_fill_text_color, resources.getColor(R.color.whiteColor));

        } catch (Exception e) {
            Log.e("ex", "Exception in custom timeline");
        } finally {
            typedArray.recycle();
        }
    }

    public void setTimeLine(int count){
        if (count == 1) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (count == 2) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (count == 3) {
            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (count == 4) {

            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
            eightyPercentTv.setBackground(fillCircleBg);
            eightyPercentTv.setTextColor(fillCircleTxtColor);
        } else if (count == 5) {


            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
            fourtyPercentTv.setBackground(fillCircleBg);
            fourtyPercentTv.setTextColor(fillCircleTxtColor);
            sixtyPercentTv.setBackground(fillCircleBg);
            sixtyPercentTv.setTextColor(fillCircleTxtColor);
            eightyPercentTv.setBackground(fillCircleBg);
            eightyPercentTv.setTextColor(fillCircleTxtColor);
            hundredPercentTv.setBackground(fillCircleBg);
            hundredPercentTv.setTextColor(fillCircleTxtColor);
        } else {

            twentyPercentTv.setBackground(fillCircleBg);
            twentyPercentTv.setTextColor(fillCircleTxtColor);
        }
    }
}
