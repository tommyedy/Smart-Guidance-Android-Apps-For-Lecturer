package com.example.smartschedulelecturer.Lecturer.Home.Adapter;

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

import com.example.smartschedulelecturer.Lecturer.Home.DetailClass;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListClassAdapter extends RecyclerView.Adapter<ListClassAdapter.ListViewHolder> {

    private Context mctx;
    private List<classData> list_classData;

    public ListClassAdapter(Context mctx, List<classData> list_classData) {
        this.mctx = mctx;
        this.list_classData = list_classData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_class_home_lecturer, parent, false);
      //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
      //  holder.classCode.setText(list_classData.get(position).getClass_code().toUpperCase());
        holder.faculty.setText(list_classData.get(position).getFaculty().toUpperCase());
        holder.section.setText(list_classData.get(position).getSection().toUpperCase());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mctx, DetailClass.class);
           //     DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            //    String getDate = dateFormat.format(list_classData.get(position).getCreated_at());
             //   i.putExtra("classCode", list_classData.get(position).getClass_code());
                i.putExtra("codeActivation", list_classData.get(position).getActivation_code());
                i.putExtra("status", list_classData.get(position).getStatus());
                i.putExtra("Section_lecturer", list_classData.get(position).getSection());
                i.putExtra("nik_lecturer", list_classData.get(position).getNik_lecturer());
                i.putExtra("nik_stat", list_classData.get(position).getNik_stat());
                i.putExtra("timestamp", list_classData.get(position).getCreated_at());
                i.putExtra("Faculty_lecturer", list_classData.get(position).getFaculty());
                mctx.startActivity(i);

            }
        });
      //  holder.imageView.
    }





    @Override
    public int getItemCount() {
        return list_classData.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{
        private TextView classCode, faculty, section;
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
