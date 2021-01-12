package com.example.tutorsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;
import com.example.tutorsapp.models.TeachingModeModel;

import java.util.ArrayList;

public class CheckboxSpinnerApater extends ArrayAdapter<TeachingModeModel> {
    private Context mContext;
    private ArrayList<TeachingModeModel> checkboxArray;
    private boolean isFromView = false;

    public CheckboxSpinnerApater(@NonNull Context context, int resource, ArrayList<TeachingModeModel> checkboxArray) {
        super(context, resource, checkboxArray);
        mContext = context;
        this.checkboxArray = checkboxArray;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.checkbox_dropdown_layout, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.textview);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(checkboxArray.get(position).getCheckboxTile());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(checkboxArray.get(position).isChecked());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
//            holder.mTextView.setText("");
//            if (isAllFalse()) {
//                holder.mTextView.setText("Select");
//            } else {
//                StringBuilder builder = new StringBuilder();
//                for (TeachingModeModel modeModel : checkboxArray) {
//                    if (modeModel.isChecked()) {
//                        builder.append(modeModel.getCheckboxTile());
//                        builder.append(",");
//
//                    }
//                }
//                holder.mTextView.setText(builder.toString().substring(0, builder.lastIndexOf(",")));
//            }

        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    checkboxArray.get(position).setChecked(isChecked);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }

    private boolean isAllFalse() {
        for (TeachingModeModel modeModel : checkboxArray) {
            if (modeModel.isChecked())
                return false;
        }
        return true;
    }
}
