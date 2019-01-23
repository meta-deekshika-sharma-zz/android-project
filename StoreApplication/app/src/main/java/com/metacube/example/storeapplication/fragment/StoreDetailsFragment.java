package com.metacube.example.storeapplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metacube.example.storeapplication.R;
import com.metacube.example.storeapplication.model.StoreHours;
import com.metacube.example.storeapplication.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class StoreDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewPagerAdapter viewPagerAdapter;
    private ViewPager viewPager;
    private String[] daysName;
    private List<StoreHours> storeHoursPojoList;
    private View leftView, rightView, presentView, middleLine;
    private TextView connectingTimeTextView, dayTextView, openingTimeTextView, closingTimeTextView;

    private OnFragmentInteractionListener mListener;

    public StoreDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StoreDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StoreDetailsFragment newInstance(String param1, String param2) {
        StoreDetailsFragment fragment = new StoreDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        storeHoursPojoList = new ArrayList<>();
        daysName = getActivity().getResources().getStringArray(R.array.days_name);
        addToStoreHours();
        final View view = inflater.inflate(R.layout.fragment_store_details, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getActivity(), storeHoursPojoList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setPadding(250, 0, 250, 0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                presentView = viewPager.findViewWithTag(i);
                enableView(presentView);
                if (i > 0 && i < viewPagerAdapter.getCount() - 1) {
                    leftView = viewPager.findViewWithTag(i - 1);
                    disableView(leftView);

                    rightView = viewPager.findViewWithTag(i + 1);
                    disableView(rightView);
                } else if (i == viewPagerAdapter.getCount() - 1) {
                    leftView = viewPager.findViewWithTag(i - 1);
                    disableView(leftView);
                } else {
                    rightView = viewPager.findViewWithTag(i + 1);
                    disableView(rightView);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        return view;
    }

    private void disableView(View view) {
        view.setAlpha(0.3f);
        middleLine = view.findViewById(R.id.middleLine);
        middleLine.setBackgroundColor(getActivity().getResources().getColor(R.color.brown_grey));
        dayTextView = view.findViewById(R.id.dayTextView);
        dayTextView.setTextColor(getActivity().getResources().getColor(R.color.brown_grey));
        connectingTimeTextView = view.findViewById(R.id.connectingTimeTextView);
        connectingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.brown_grey));
        openingTimeTextView = view.findViewById(R.id.openingTimeTextView);
        openingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.brown_grey));
        closingTimeTextView = view.findViewById(R.id.closingTimeTextView);
        closingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.brown_grey));
    }

    private void enableView(View view) {
        view.setAlpha(1f);
        middleLine = view.findViewById(R.id.middleLine);
        middleLine.setBackgroundColor(getActivity().getResources().getColor(R.color.rouge));
        dayTextView = view.findViewById(R.id.dayTextView);
        dayTextView.setTextColor(getActivity().getResources().getColor(R.color.rouge));
        connectingTimeTextView = view.findViewById(R.id.connectingTimeTextView);
        connectingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.rouge));
        openingTimeTextView = view.findViewById(R.id.openingTimeTextView);
        openingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.rouge));
        closingTimeTextView = view.findViewById(R.id.closingTimeTextView);
        closingTimeTextView.setTextColor(getActivity().getResources().getColor(R.color.rouge));
    }

    private void addToStoreHours() {
        storeHoursPojoList.add(new StoreHours(daysName[0], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[1], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[2], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[3], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[4], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[5], "09:00 AM", "10:30 AM"));
        storeHoursPojoList.add(new StoreHours(daysName[6], "09:00 AM", "10:30 AM"));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
