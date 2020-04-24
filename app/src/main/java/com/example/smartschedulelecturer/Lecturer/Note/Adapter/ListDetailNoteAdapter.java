package com.example.smartschedulelecturer.Lecturer.Note.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Model.note;
import com.example.smartschedulelecturer.R;

import java.util.List;

public class ListDetailNoteAdapter extends RecyclerView.Adapter<ListDetailNoteAdapter.ListViewHolder> {
    private Context mctx;
    private List<note> list_noteData;

    public ListDetailNoteAdapter(Context mctx, List<note> list_noteData) {
        this.mctx = mctx;
        this.list_noteData = list_noteData;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_detail_note_lecturer, parent, false);
        //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.nim_students.setText(list_noteData.get(position).getClassCode().toUpperCase());
        holder.faculty.setText(list_noteData.get(position).getNim_students().toUpperCase());
        holder.section.setText(list_noteData.get(position).getNote().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return list_noteData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        private TextView faculty, section, nim_students;
        private ImageView imageView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            nim_students = itemView.findViewById(R.id.codeClass_id);
            faculty = itemView.findViewById(R.id.faculty_id);
            section = itemView.findViewById(R.id.jurusan_id);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
