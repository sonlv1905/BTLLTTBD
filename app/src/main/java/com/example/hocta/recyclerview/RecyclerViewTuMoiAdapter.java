package com.example.hocta.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.telecom.PhoneAccount;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hocta.MainActivity;
import com.example.hocta.R;
import com.example.hocta.UpdateRemoveTuMoiActivity;
import com.example.hocta.model.TuMoi;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTuMoiAdapter extends RecyclerView.Adapter<RecyclerViewTuMoiAdapter.CardView> {

    private Context context;
    private List<TuMoi> mList;

    public RecyclerViewTuMoiAdapter(Context context){
        this.context = context;
        mList = new ArrayList<>();
    }

    public void setData(List<TuMoi> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void addTuMoi(TuMoi tuMoi){
        this.mList.add(tuMoi);
        notifyDataSetChanged();
    }

    @Override
    public CardView onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tu_moi_card, parent, false);
        return new CardView(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerViewTuMoiAdapter.CardView holder, int position) {
        TuMoi tuMoi = mList.get(position);
        holder.tvTu.setText(tuMoi.getTu());
        holder.tvLoaiTu.setText(tuMoi.getLoaiTu());
        holder.tvNghia.setText(tuMoi.getNghia());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateRemoveTuMoiActivity.class);
                intent.putExtra("tumoi", tuMoi);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CardView extends RecyclerView.ViewHolder {
        TextView tvTu, tvLoaiTu, tvNghia;
        public CardView(@NonNull View itemView) {
            super(itemView);
            tvTu = itemView.findViewById(R.id.tvTu);
            tvLoaiTu = itemView.findViewById(R.id.tvLoaiTu);
            tvNghia = itemView.findViewById(R.id.tvNghia);
        }
    }
}
