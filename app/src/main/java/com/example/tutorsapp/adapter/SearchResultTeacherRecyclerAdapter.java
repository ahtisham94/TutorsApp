package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.AssignmentHistoryModel;
import com.example.tutorsapp.models.SearchResultTeacherModel;

import java.util.List;

public class SearchResultTeacherRecyclerAdapter extends RecyclerView.Adapter<SearchResultTeacherRecyclerAdapter.ViewHolder> {
    List<SearchResultTeacherModel> searchResultList;

    public SearchResultTeacherRecyclerAdapter(List<SearchResultTeacherModel> list) {
        this.searchResultList = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_search_result_teacher, parent, false);
        return new SearchResultTeacherRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultTeacherRecyclerAdapter.ViewHolder holder, int position) {
        holder.setData(searchResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResultList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv;
        TextView distanceTV;
        TextView feeTV;
        TextView qualificationTV;
        TextView addressTV;
        RatingBar ratingBar;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            distanceTV = itemView.findViewById(R.id.distanceTV);
            feeTV = itemView.findViewById(R.id.feeTV);
            qualificationTV = itemView.findViewById(R.id.qualificationTV);
            addressTV = itemView.findViewById(R.id.addressTV);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        void setData(SearchResultTeacherModel result) {
            nameTv.setText(result.getName());
            distanceTV.setText(result.getDistance());
            feeTV.setText(result.getFee());
            qualificationTV.setText(result.getQualification());
            addressTV.setText(result.getAddress());
            ratingBar.setRating(5);
        }
    }
}
