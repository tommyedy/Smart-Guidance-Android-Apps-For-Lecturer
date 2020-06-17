package com.example.smartschedulelecturer.Lecturer.FriendRequest.Adapter;

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

import java.util.List;

public class ListPeopleRequestAdapter extends RecyclerView.Adapter<ListPeopleRequestAdapter.ListPeople> {
    private Context mctx;
    private List<detailClass> list_detail_class;

    public ListPeopleRequestAdapter(Context mctx, List<detailClass> list_detail_class) {
        this.mctx = mctx;
        this.list_detail_class = list_detail_class;
    }

    @NonNull
    @Override
    public ListPeople onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_people_request, parent, false);
        //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new ListPeople(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListPeople holder, int position) {
        holder.students_nim.setText(list_detail_class.get(position).getNim_students().toUpperCase());
        holder.students_name.setText(list_detail_class.get(position).getStudents_name().toUpperCase());
      //  holder.faculty.setText(list_detail_class.get(position).getFaculty().toUpperCase());
      //  holder.section.setText(list_detail_class.get(position).getSection().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return list_detail_class.size();
    }

    class ListPeople extends RecyclerView.ViewHolder{
        private TextView students_nim, students_name, faculty, section;
        private ImageView imageView;

        public ListPeople(@NonNull View itemView) {
            super(itemView);
            students_nim = itemView.findViewById(R.id.students_nim);
            students_name = itemView.findViewById(R.id.students_name);
            faculty = itemView.findViewById(R.id.faculty_id);
            section = itemView.findViewById(R.id.section_id);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
