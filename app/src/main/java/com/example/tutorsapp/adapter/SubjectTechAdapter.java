package com.example.tutorsapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.LOVResponseModel;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class SubjectTechAdapter extends Section {
    List<LOVResponseModel> stringList;
    String title;
    private final ClickListener clickListener;
    private SectionedRecyclerViewAdapter adapter;

    public SubjectTechAdapter(@NonNull final String title, @NonNull final List<LOVResponseModel> list, SectionedRecyclerViewAdapter adapter, @NonNull final ClickListener clickListener) {
        super(SectionParameters.builder().headerResourceId(R.layout.subject_teach_header_item)
                .itemResourceId(R.layout.subject_tech_item_layout).build());
        this.stringList = list;
        this.title = title;
        this.clickListener = clickListener;
        this.adapter = adapter;

    }

    @Override
    public int getContentItemsTotal() {
        return stringList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new SubjectItemsHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((SubjectItemsHolder) holder).subItemTv.setText(stringList.get(position).getName());
        ((SubjectItemsHolder) holder).delSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(holder.getAdapterPosition());
                clickListener.onItemRootViewClicked(SubjectTechAdapter.this, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        HeaderHolder headerHolder = (HeaderHolder) holder;
        String[] header = title.split(",");
        headerHolder.headerTv.setText(header[1]);

    }

    private class SubjectItemsHolder extends RecyclerView.ViewHolder {
        TextView subItemTv, delSub;

        public SubjectItemsHolder(@NonNull View itemView) {
            super(itemView);
            subItemTv = itemView.findViewById(R.id.subjectTechesItem);
            delSub = itemView.findViewById(R.id.delSubject);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView headerTv;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            headerTv = itemView.findViewById(R.id.gradsTechesItemHeader);
        }
    }

    public interface ClickListener {

        void onItemRootViewClicked(@NonNull final SubjectTechAdapter section, final int itemAdapterPosition);
    }

    public LOVResponseModel getItem(int postion) {
        if (adapter.getPositionInSection(postion) != -1)
            return stringList.get(adapter.getPositionInSection(postion));
        return null;
    }

    public void removeItem(int position) {
        stringList.remove(adapter.getPositionInSection(position));

    }

    public String getTitle() {
        return title;
    }
}
