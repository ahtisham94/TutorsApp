package com.example.tutorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tutorsapp.R;

public class MainScreenSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;


    private Integer[] bgImages = {R.drawable.group3,
            R.drawable.image1,
            R.drawable.image2};


    private Integer[] textImages = {R.drawable.providing_teacher_opportunities,
            R.drawable.find_student,
            R.drawable.earn_extra_income};

    public MainScreenSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bgImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.li_main_slider_image, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(bgImages[position]);

        ImageView imageViewText = (ImageView) view.findViewById(R.id.imageViewText);
        imageViewText.setImageResource(textImages[position]);


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
