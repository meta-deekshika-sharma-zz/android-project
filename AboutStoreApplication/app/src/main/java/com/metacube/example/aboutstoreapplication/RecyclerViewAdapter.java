package com.metacube.example.aboutstoreapplication;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<StoreService> storeServiceList;
    private Context context;

    public RecyclerViewAdapter(List<StoreService> storeServiceList, Context context) {
        this.storeServiceList = storeServiceList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        StoreService storeService = storeServiceList.get(i);
        viewHolder.recyclerViewItemImage.setImageResource(storeService.getImage());
        viewHolder.recyclerViewItemImage.setColorFilter(context.getResources().getColor(R.color.white));
        viewHolder.recyclerViewItemName.setText(storeService.getName());
    }

    @Override
    public int getItemCount() {
        return storeServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView recyclerViewItemImage;
        public TextView recyclerViewItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerViewItemImage = itemView.findViewById(R.id.recyclerViewItemImage);
            recyclerViewItemName = itemView.findViewById(R.id.recyclerViewItemName);
        }
    }
}
