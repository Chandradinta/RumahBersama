package com.example.rumahbersama.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dwiki.rsplg.R;
import com.example.rumahbersama.Activity.ModelRumahBersama;

import java.util.List;

public class AdapterRumahBersama extends RecyclerView.Adapter<AdapterRumahBersama.HolderData> {
    private Context ctx;
    private List<ModelRumahBersama> listRumahBersama;

    public AdapterRumahBersama(Context ctx, List<ModelRumahBersama> listRumahBersama) {
        this.ctx = ctx;
        this.listRumahBersama = listRumahBersama;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rumah_bersama, parent,false);
        HolderData holder = new HolderData(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelRumahBersama MRS = listRumahBersama.get(position);

        holder.tvNama.setText(MRS.getNama());
        holder.tvAlamat.setText(MRS.getAlamat());
        holder.tvTelepon.setText(MRS.getTelepon());
        holder.tvKoordinat.setText(MRS.getKoordinat());
        Glide.with(ctx)
                .load(MRS.getFoto())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.ivRumahBersama);


    }

    @Override
    public int getItemCount() {
        return listRumahBersama.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvNama, tvAlamat, tvTelepon, tvKoordinat;
        ImageView ivRumahBersama;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
            tvTelepon = itemView.findViewById(R.id.tv_telepon);
            tvKoordinat = itemView.findViewById(R.id.tv_koordinat);
            ivRumahBersama = itemView.findViewById(R.id.iv_rumah_bersama);


        }
    }
}


