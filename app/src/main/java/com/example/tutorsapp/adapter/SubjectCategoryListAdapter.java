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

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.RequiresApi;

public class SubjectCategoryListAdapter extends BaseExpandableListAdapter {
    private ArrayList<LOVCategoryResponseModel> arrayList, dataList;
    private Context _context;
    private final Set<Pair<Long, Long>> mCheckedItems = new HashSet<Pair<Long, Long>>();
    private HashMap<String, List<LOVResponseModel>> hashMap;

    public SubjectCategoryListAdapter(ArrayList<LOVCategoryResponseModel> arrayList, Context _context) {
        this.arrayList = arrayList;
        this._context = _context;
        dataList = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    @Override
    public int getGroupCount() {
        return this.arrayList.size();
    }

    @Override
    public int getChildrenCount(int childCount) {
        return this.arrayList.get(childCount).getCatList().size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.arrayList.get(groupPosition).getCatList().get(childPosititon);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.li_alert_expandable_list_text, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.label);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(arrayList.get(groupPosition).getName());
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

                    LOVCategoryResponseModel model = (LOVCategoryResponseModel) getGroup(groupPosition);
                    if (hashMap.containsKey(model.getId() + "," + model.getName())) {
                        LOVResponseModel item = (LOVResponseModel) getChild(groupPosition, childPosition);
                        List<LOVResponseModel> list = hashMap.get(model.getId() + "," + model.getName());
                        assert list != null;
                        list.add(item);

                    } else {
                        List<LOVResponseModel> list = new ArrayList<>();
                        LOVResponseModel item = (LOVResponseModel) getChild(groupPosition, childPosition);
                        list.add(item);
                        hashMap.put(model.getId() + "," + model.getName(), list);
                    }


                } else {
                    mCheckedItems.remove(tag);
                    LOVCategoryResponseModel model = (LOVCategoryResponseModel) getGroup(groupPosition);
                    LOVResponseModel item = (LOVResponseModel) getChild(groupPosition, childPosition);

                    if (Objects.requireNonNull(hashMap.get(model.getId() + "," + model.getName())).size() == 0) {
                        hashMap.remove(model.getId());
                    } else {
                        Objects.requireNonNull(hashMap.get(model.getId()+","+model.getName())).remove(item);
                    }
                }
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public HashMap<String, List<LOVResponseModel>> getHashMap() {
        return hashMap;
    }
}
