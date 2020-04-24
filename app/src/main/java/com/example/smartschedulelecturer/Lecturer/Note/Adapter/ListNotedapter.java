package com.example.smartschedulelecturer.Lecturer.Note.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.Note.DetailNoteLecturer;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListNotedapter extends RecyclerView.Adapter<ListNotedapter.ListViewHolder> {
    private Context mctx;
    private List<classData> listClassData;


    public ListNotedapter(Context mctx, List<classData> listClassData) {
        this.mctx = mctx;
        this.listClassData = listClassData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_note_lecturer, parent, false);
        //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
       // holder.classCode.setText(listClassData.get(position).getClass_code().toUpperCase());
        holder.faculty.setText(listClassData.get(position).getFaculty().toUpperCase());
        holder.section.setText(listClassData.get(position).getSection().toUpperCase());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mctx, DetailNoteLecturer.class);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String getDate = dateFormat.format(listClassData.get(position).getCreated_at());
             //   i.putExtra("classCode", listClassData.get(position).getClass_code());
//                i.putExtra("codeActivation", listClassData.get(position).getActivation_code());
//                i.putExtra("status", listClassData.get(position).getStatus());
//                i.putExtra("Section_lecturers", listClassData.get(position).getSection());
//                i.putExtra("nik_lecturer", listClassData.get(position).getNik_lecturer());
//                i.putExtra("nik_stat", listClassData.get(position).getNik_stat());
//                i.putExtra("timestamp", getDate);
//                i.putExtra("Faculty_lecturer", listClassData.get(position).getFaculty());
                mctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listClassData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView classCode, faculty, section, Schedule_id;
        private ImageView imageView;
        private CardView linearLayout;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            classCode = itemView.findViewById(R.id.codeClass_id);
            faculty = itemView.findViewById(R.id.faculty_id);
            section = itemView.findViewById(R.id.jurusan_id);
            imageView = itemView.findViewById(R.id.imageView);
            linearLayout= itemView.findViewById(R.id.home_mRide);
        }
    }
}
