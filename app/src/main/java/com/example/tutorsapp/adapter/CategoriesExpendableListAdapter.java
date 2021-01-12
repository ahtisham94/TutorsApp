package com.example.tutorsapp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.LOVResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CategoriesExpendableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<LOVResponseModel> _listDataHeader, dataList; // header titles

    public HashMap<LOVResponseModel, List<LOVResponseModel>> getSubjectTechsHashmap() {
        return subjectTechsHashmap;
    }

    // child data in format of header title, child title
    public HashMap<LOVResponseModel, List<LOVResponseModel>> _listDataChild, subjectTechsHashmap;
    private final Set<Pair<Long, Long>> mCheckedItems = new HashSet<Pair<Long, Long>>();

    public Set<Pair<Long, Long>> getmCheckedItems() {
        return mCheckedItems;
    }

    public CategoriesExpendableListAdapter(Context context, List<LOVResponseModel> listDataHeader,
                                           HashMap<LOVResponseModel, List<LOVResponseModel>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        subjectTechsHashmap = new HashMap<>();
        dataList = new ArrayList<>();
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, final ViewGroup parent) {

        final LOVResponseModel childText = (LOVResponseModel) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.li_alert_expandable_list_education, null);
        }
        View childView = convertView.findViewById(R.id.itemViewChild);
        CheckBox checkBox = convertView.findViewById(R.id.checkCb);
        TextView txtListChild = convertView.findViewById(R.id.subjectTv);
        txtListChild.setText(childText.getName());
        // add tag to remember groupId/childId
        final Pair<Long, Long> tag = new Pair<Long, Long>(
                getGroupId(groupPosition),
                getChildId(groupPosition, childPosition));
        checkBox.setTag(tag);
        // set checked if groupId/childId in checked items
        checkBox.setChecked(mCheckedItems.contains(tag));
        childView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                CheckBox checkBox1 = view.findViewById(R.id.checkCb);
                checkBox1.setChecked(!checkBox1.isChecked());
                TextView textView = view.findViewById(R.id.subjectTv);
                final Pair<Long, Long> tag = (Pair<Long, Long>) checkBox1.getTag();
                if (checkBox1.isChecked()) {
                    Toast.makeText(_context, textView.getText().toString(), Toast.LENGTH_SHORT).show();
                    mCheckedItems.add(tag);
                    if (subjectTechsHashmap.containsKey((LOVResponseModel) getGroup(groupPosition))) {
                        dataList = subjectTechsHashmap.get((LOVResponseModel) getGroup(groupPosition));
                        dataList.add(childText);

                    } else {
                        dataList = new ArrayList<>();
                        dataList.add(childText);
                        subjectTechsHashmap.put((LOVResponseModel) getGroup(groupPosition), dataList);

                    }
                } else {
                    mCheckedItems.remove(tag);
                    if (subjectTechsHashmap.get((LOVResponseModel) getGroup(groupPosition)).size() != 0) {
                        Objects.requireNonNull(subjectTechsHashmap.get((LOVResponseModel) getGroup(groupPosition))).remove(textView.getText().toString());
                    } else
                        subjectTechsHashmap.remove((LOVResponseModel) getGroup(groupPosition));
                    if (Objects.requireNonNull(subjectTechsHashmap.get((LOVResponseModel) getGroup(groupPosition))).size() == 0)
                        subjectTechsHashmap.remove((LOVResponseModel) getGroup(groupPosition));
                }
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        LOVResponseModel headerTitle = (LOVResponseModel) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.li_alert_expandable_list_text, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.label);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle.getName());
        final ImageView nonExpandedListIv = convertView.findViewById(R.id.nonExpandedListIv);
        final ImageView expandedListIv = convertView.findViewById(R.id.expandedListIv);

        if (isExpanded) {
            nonExpandedListIv.setVisibility(View.GONE);
            expandedListIv.setVisibility(View.VISIBLE);

        } else {
            nonExpandedListIv.setVisibility(View.VISIBLE);
            expandedListIv.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
