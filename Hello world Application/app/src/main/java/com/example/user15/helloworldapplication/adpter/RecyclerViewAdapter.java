package com.example.user15.helloworldapplication.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user15.helloworldapplication.pojo.ListItem;
import com.example.user15.helloworldapplication.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public RecyclerViewAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ListItem listItem = listItems.get(i);
        viewHolder.imageViewFlag.setImageResource(listItem.getFlagImage());
        viewHolder.textViewFlag.setText(listItem.getFlagText());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewFlag;
        public TextView textViewFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewFlag = itemView.findViewById(R.id.imageViewFlag);
            textViewFlag = itemView.findViewById(R.id.textViewFlag);
        }
    }
}
