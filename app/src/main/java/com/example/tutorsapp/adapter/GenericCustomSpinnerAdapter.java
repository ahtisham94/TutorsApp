package com.example.tutorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.LOVResponseModel;

import java.util.ArrayList;

public class GenericCustomSpinnerAdapter extends ArrayAdapter<LOVResponseModel> {

    ArrayList<LOVResponseModel> arrayList;

    public GenericCustomSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, ArrayList<LOVResponseModel> arrayList) {
        super(context, resource, textViewResourceId, arrayList);
        this.arrayList = arrayList;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.spinner_item_layout, parent, false);
        TextView textView = view.findViewById(R.id.spinner_item_tv);
        textView.setText(arrayList.get(position).getName());


        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_layout, parent, false);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.spinner_item_tv);
        TextView spinnerItemValue = (TextView) convertView.findViewById(R.id.spinner_item_tv_value);
        txtTitle.setText(arrayList.get(position).getName());
//        spinnerItemValue.setText(arrayList.get(position).getTimeValue());

        return convertView;

    }

    public void setArrayList(ArrayList<LOVResponseModel> arrayList) {
        this.arrayList.clear();
        this.arrayList = arrayList;
        notifyDataSetChanged();

    }
}
