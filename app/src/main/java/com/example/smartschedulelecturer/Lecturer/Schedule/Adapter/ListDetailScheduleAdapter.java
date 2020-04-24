package com.example.smartschedulelecturer.Lecturer.Schedule.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.Schedule.EditScheduleLecturer;
import com.example.smartschedulelecturer.Model.schedule;
import com.example.smartschedulelecturer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListDetailScheduleAdapter extends RecyclerView.Adapter<ListDetailScheduleAdapter.listdetailschedule> {
    private Context mctx;
    private List<schedule> listscheduledata;

    public ListDetailScheduleAdapter(Context mctx, List<schedule> listscheduledata) {
        this.mctx = mctx;
        this.listscheduledata = listscheduledata;
    }

    @NonNull
    @Override
    public listdetailschedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =  inflater.inflate(R.layout.list_detail_schedule_lecturer, parent, false);
        //  return new ListViewHolder(LayoutInflater.from(mctx).inflate(R.layout.fragment_home_lecturer, parent, false));
        return new listdetailschedule(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listdetailschedule holder, int position) {
        holder.classCode.setText(listscheduledata.get(position).getTitle_guidance().toUpperCase());
        holder.faculty.setText(listscheduledata.get(position).getTime().toUpperCase());
        holder.section.setText(listscheduledata.get(position).getDate().toUpperCase());
        holder.place.setText(listscheduledata.get(position).getPlaces().toUpperCase());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mctx, EditScheduleLecturer.class);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String getDate = dateFormat.format(listscheduledata.get(position).getCreated_at());
                i.putExtra("titleGuidance", listscheduledata.get(position).getTitle_guidance());
                i.putExtra("time", listscheduledata.get(position).getTime());
                i.putExtra("date", listscheduledata.get(position).getDate());
                i.putExtra("places", listscheduledata.get(position).getPlaces());
                mctx.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listscheduledata.size();
    }

    class listdetailschedule extends RecyclerView.ViewHolder{
        private TextView classCode, faculty, section, place;
        private CardView linearLayout;

        public listdetailschedule(@NonNull View itemView) {
            super(itemView);
            classCode = itemView.findViewById(R.id.title_guidance);
            faculty = itemView.findViewById(R.id.time_id);
            section = itemView.findViewById(R.id.day_date_id);
            place = itemView.findViewById(R.id.place_id);
            linearLayout= itemView.findViewById(R.id.home_mRide);
        }
    }
}
