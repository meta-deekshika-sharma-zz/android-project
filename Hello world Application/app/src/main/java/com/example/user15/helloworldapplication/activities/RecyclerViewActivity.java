package com.example.user15.helloworldapplication.activities;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user15.helloworldapplication.pojo.ListItem;
import com.example.user15.helloworldapplication.R;
import com.example.user15.helloworldapplication.adpter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        getSupportActionBar().setTitle("Recycler View");

        recyclerView = findViewById(R.id.recyclerView);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        listItems = new ArrayList<>();

        addListItems();
        adapter = new RecyclerViewAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }

    private void addListItems() {
        listItems.add(new ListItem(R.drawable.flag_of_india, "India"));
        listItems.add(new ListItem(R.drawable.flag_of_england, "England"));
        listItems.add(new ListItem(R.drawable.australia_image, "Australia"));
        listItems.add(new ListItem(R.drawable.american_sticker, "United States of America"));
    }
}
