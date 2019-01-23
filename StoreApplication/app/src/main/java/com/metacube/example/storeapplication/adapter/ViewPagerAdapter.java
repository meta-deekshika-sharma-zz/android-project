package com.metacube.example.storeapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metacube.example.storeapplication.R;
import com.metacube.example.storeapplication.model.StoreHours;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<StoreHours> storeHoursPojoList;
    private LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, List<StoreHours> storeHoursPojoList) {
        this.context = context;
        this.storeHoursPojoList = storeHoursPojoList;
    }

    @Override
    public int getCount() {
        return storeHoursPojoList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_pager_item, container, false);

        TextView dayTextView, openingTimeTextView, closingTimeTextView;

        dayTextView = view.findViewById(R.id.dayTextView);
        openingTimeTextView = view.findViewById(R.id.openingTimeTextView);
        closingTimeTextView = view.findViewById(R.id.closingTimeTextView);

        dayTextView.setText(storeHoursPojoList.get(position).getDay());
        openingTimeTextView.setText(storeHoursPojoList.get(position).getOpeningTime());
        closingTimeTextView.setText(storeHoursPojoList.get(position).getClosingTime());

        view.setTag(position);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
