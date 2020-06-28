package com.example.notdefteri.NotListesi.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notdefteri.NotDetay.view.NotDetayActivity;
import com.example.notdefteri.NotEkle.model.Not;
import com.example.notdefteri.NotEkle.model.NotModel;
import com.example.notdefteri.R;

import java.util.ArrayList;
import java.util.List;

public class NotlarAdapter extends RecyclerView.Adapter<NotlarAdapter.MyViewHolder> {
    List<NotModel> notlarArrayList;
    LayoutInflater inflater;
    Context context;



    public NotlarAdapter(ArrayList<NotModel> notlarArrayList, Context context) {
        this.notlarArrayList = notlarArrayList;
        this.context=context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.notlar_item, parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

    holder.baslik_tv.setText(notlarArrayList.get(position).getBaslik());
    holder.aciklama_tv.setText(notlarArrayList.get(position).getAciklama());
    holder.tarih_tv.setText(notlarArrayList.get(position).getTarih());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(context , NotDetayActivity.class);
            intent.putExtra("Key_Id",notlarArrayList.get(position).getNotId());
            intent.putExtra("Key_Baslik",notlarArrayList.get(position).getBaslik());
            intent.putExtra("Key_Aciklama",notlarArrayList.get(position).getAciklama());
            intent.putExtra("Key_Tarih",notlarArrayList.get(position).getTarih());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return notlarArrayList.size();
    }

    public void setNotlist(List<NotModel> notList) {
        notlarArrayList= notList;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView baslik_tv ,aciklama_tv ,tarih_tv ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            baslik_tv = itemView.findViewById(R.id.baslik_tv);
            aciklama_tv=itemView.findViewById(R.id.aciklama_tv);
            tarih_tv=itemView.findViewById(R.id.tarih_tv);

        }
    }
}
