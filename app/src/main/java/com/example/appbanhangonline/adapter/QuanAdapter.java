package com.example.appbanhangonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbanhangonline.R;
import com.example.appbanhangonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class QuanAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham>arrayListquan;

    public QuanAdapter(Context context, ArrayList<Sanpham> arrayListquan) {
        this.context = context;
        this.arrayListquan = arrayListquan;
    }

    @Override
    public int getCount() {
        return arrayListquan.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListquan.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder {
        public TextView txttenquan,txtgiaquan,txtmotaquan;
        public ImageView imgquan;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       QuanAdapter.ViewHolder viewHolder =null;
        if (convertView==null){
            viewHolder=new QuanAdapter.ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_quan,null);
            viewHolder.txttenquan=convertView.findViewById(R.id.textviewtenquan);
            viewHolder.txtgiaquan=convertView.findViewById(R.id.textviewgiaquan);
            viewHolder.txtmotaquan=convertView.findViewById(R.id.textviewmotaquan);
            viewHolder.imgquan=convertView.findViewById(R.id.imagequan);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (QuanAdapter.ViewHolder) convertView.getTag();
        }
        Sanpham sanpham= (Sanpham) getItem(position);
        viewHolder.txttenquan.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat =new DecimalFormat("###,###,###");
        viewHolder.txtgiaquan.setText("Giá: "+ decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotaquan.setMaxLines(2);
        viewHolder.txtmotaquan.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaquan.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgquan);
        return convertView;
    }
}
