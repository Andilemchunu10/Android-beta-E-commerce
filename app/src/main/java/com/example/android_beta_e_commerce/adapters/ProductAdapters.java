package com.example.android_beta_e_commerce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_beta_e_commerce.R;
import com.example.android_beta_e_commerce.model.Model;

import java.util.List;

public class ProductAdapters extends RecyclerView.Adapter<ProductAdapters.ViewHolder> {

    private Context context;
    private List<Model> modelList;

    public ProductAdapters(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(modelList.get(position).getProductImg()).into(holder.productImage);
        holder.productName.setText(modelList.get(position).getProductName());
        holder.productPrice.setText(modelList.get(position).getProductPrice());


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName,productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.productImg);
            productName= itemView.findViewById(R.id.productName);
            productPrice= itemView.findViewById(R.id.productPrice);
        }
    }
}
