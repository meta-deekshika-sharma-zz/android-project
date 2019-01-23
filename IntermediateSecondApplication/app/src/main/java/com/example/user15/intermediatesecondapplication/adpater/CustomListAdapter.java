package com.example.user15.intermediatesecondapplication.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user15.intermediatesecondapplication.R;
import com.example.user15.intermediatesecondapplication.model.Flower;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {

    private List<Flower> flowerList;
    private Context context;

    public CustomListAdapter(List<Flower> flowerList, Context context) {
        this.flowerList = flowerList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomListAdapter.ViewHolder viewHolder, int i) {

        Flower flower = flowerList.get(i);
        viewHolder.circularImageView.setImageBitmap(flower.getBitmap());
        viewHolder.flowerTextView.setText(flower.getName());
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CircularImageView circularImageView;
        public TextView flowerTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circularImageView = itemView.findViewById(R.id.circularImageView);
            flowerTextView = itemView.findViewById(R.id.flowerTextView);
        }
    }
}
