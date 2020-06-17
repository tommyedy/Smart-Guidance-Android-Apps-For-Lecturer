package com.example.smartschedulelecturer.Lecturer.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Model.detailClass;
import com.example.smartschedulelecturer.R;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListClassDetailAdapter extends RecyclerView.Adapter<ListClassDetailAdapter.ListViewHolder> {
    private Context mctx;
    private List<detailClass> listDetailClass;


    public ListClassDetailAdapter(Context mctx, List<detailClass> listDetailClass) {
        this.mctx = mctx;
        this.listDetailClass = listDetailClass;
    }


//    public ListClassDetailAdapter(){
//
//    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_people_request, parent, false);
        //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.students_nim.setText(listDetailClass.get(position).getNim_students().toUpperCase());
        holder.students_name.setText(listDetailClass.get(position).getStudents_name().toUpperCase());
        holder.faculty.setText(listDetailClass.get(position).getFaculty().toUpperCase());
        holder.section.setText(listDetailClass.get(position).getFaculty().toUpperCase());

    }

    @Override
    public int getItemCount() {
        return listDetailClass.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView students_nim, students_name, faculty, section;
        private ImageView imageView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            students_nim = itemView.findViewById(R.id.students_nim);
            students_name = itemView.findViewById(R.id.students_name);
            faculty = itemView.findViewById(R.id.faculty_id);
            section = itemView.findViewById(R.id.section_id);
            //imageView = itemView.findViewById(R.id.imageView);

        }


    }
}
