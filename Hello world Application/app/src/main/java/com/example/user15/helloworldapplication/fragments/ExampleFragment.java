package com.example.user15.helloworldapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user15.helloworldapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ExampleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExampleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ExampleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExampleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExampleFragment newInstance(String param1, String param2) {
        ExampleFragment fragment = new ExampleFragment();
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


        Log.d("1234", "onCreate Fragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("1234", "onCreateView");
        return inflater.inflate(R.layout.fragment_example, container, false);
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

        Log.d("1234", "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("1234", "onDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("1234", "onStart Fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("1234", "onPause Fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("1234", "onResume Fragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("1234", "onStop Fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("1234", "onDestroy Fragment");
    }
}
