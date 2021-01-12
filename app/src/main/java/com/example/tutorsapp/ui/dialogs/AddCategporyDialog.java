package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.adapter.SubjectCategoryListAdapter;
import com.example.tutorsapp.models.LOVCategoryResponseModel;
import com.example.tutorsapp.models.LOVResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddCategporyDialog extends Dialog implements View.OnClickListener {
    private SubjectCategoryListAdapter adapter;
    private ExpandableListView expandableListView;
    private TextView doneTv;
    private GetSubList getSubList;
    private final Set<Pair<Long, Long>> mCheckedItems = new HashSet<Pair<Long, Long>>();
    private ArrayList<LOVResponseModel> headerArray;
    private int i = 0;
    private ArrayList<LOVCategoryResponseModel> subjectList;

    public AddCategporyDialog(@NonNull Context context, GetSubList getSubList, ArrayList<LOVCategoryResponseModel> arrayList) {
        super(context, R.style.full_screen_dialog);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.li_alert_institution_categories);
        if (getWindow() != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(getWindow().getAttributes());
            lp.width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.90);
            lp.height = (int) (context.getResources().getDisplayMetrics().heightPixels * 0.70);
            getWindow().setAttributes(lp);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        }
        expandableListView = findViewById(R.id.categoriesEl);
        doneTv = findViewById(R.id.doneBtn);
        doneTv.setOnClickListener(this);
        this.getSubList = getSubList;
        headerArray = new ArrayList<>();
        subjectList = arrayList;
        adapter = new SubjectCategoryListAdapter(subjectList, context);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.doneBtn) {
            if (!adapter.getHashMap().isEmpty()) {
                getSubList.SubList(adapter.getHashMap());
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please select Atleast one subject", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface GetSubList {
        public void SubList(HashMap<String, List<LOVResponseModel>> listHashMap);
    }
}
