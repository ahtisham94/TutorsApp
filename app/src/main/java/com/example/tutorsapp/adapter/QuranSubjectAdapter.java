package com.example.tutorsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorsapp.R;
import com.example.tutorsapp.models.ArrayEmpty;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuranSubjectAdapter extends RecyclerView.Adapter<QuranSubjectAdapter.MyQuranSubjectsHolder> {
    ArrayList<String> stringsArray;
    ArrayEmpty arrayEmpty;

    public QuranSubjectAdapter(ArrayList<String> stringsArray, ArrayEmpty arrayEmpty) {
        this.stringsArray = stringsArray;
        this.arrayEmpty = arrayEmpty;
    }

    @NonNull
    @Override
    public MyQuranSubjectsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quran_subjects_item_layout, parent, false);
        return new MyQuranSubjectsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyQuranSubjectsHolder holder, int position) {
        holder.subjectItemTv.setText(stringsArray.get(holder.getAdapterPosition()));
        holder.cancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringsArray.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                if (stringsArray.size() == 0)
                    arrayEmpty.empty();
            }
        });
    }

    public void setStringsArray(ArrayList<String> stringsArray) {
        this.stringsArray = stringsArray;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return stringsArray.size();
    }

    class MyQuranSubjectsHolder extends RecyclerView.ViewHolder {
        TextView subjectItemTv;
        ImageView cancelImg;

        public MyQuranSubjectsHolder(@NonNull View itemView) {
            super(itemView);
            subjectItemTv = itemView.findViewById(R.id.subjectItemTv);
            cancelImg = itemView.findViewById(R.id.cancelImg);
        }
    }

}
