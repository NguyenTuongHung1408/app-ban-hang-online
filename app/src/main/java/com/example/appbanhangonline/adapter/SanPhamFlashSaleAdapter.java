package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.activity.ChiTietSanPhamActivity;
import com.example.appbanhangonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.appbanhangonline.ultil.checkconnect;

public class SanPhamFlashSaleAdapter extends RecyclerView.Adapter<SanPhamFlashSaleAdapter.ITemHolder> {
    Context context;
    ArrayList<Sanpham> arraysanpham;

    public SanPhamFlashSaleAdapter(Context context, ArrayList<Sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @NonNull
    @Override
    public ITemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_sanphamflashsale,null);
        ITemHolder iTemHolder=new ITemHolder(v);

        return iTemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ITemHolder iTemHolder, int i) {
        Sanpham sanpham =arraysanpham.get(i);
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        iTemHolder.txtgiasanpham.setText(decimalFormat.format(sanpham.getGiasanpham())+" VNĐ");
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(iTemHolder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ITemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txtgiasanpham;

        public ITemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham =itemView.findViewById(R.id.anhSanPham);
            txtgiasanpham =itemView.findViewById(R.id.giaSanPham);
            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(context, ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",arraysanpham.get(getAdapterPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                checkconnect.ShowToast_Short(context,arraysanpham.get(getAdapterPosition()).getTensanpham());
                context.startActivity(intent);

            });
        }
    }
}
