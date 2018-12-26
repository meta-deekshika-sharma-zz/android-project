package com.example.user15.helloworldapplication.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.user15.helloworldapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowFormDataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowFormDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowFormDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView name, email, number, dob, gender, state, city;
    private CheckBox aadharCard, pancard, voterCard, drivingLicence;

    private OnFragmentInteractionListener mListener;

    public ShowFormDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowFormDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowFormDataFragment newInstance(String param1, String param2) {
        ShowFormDataFragment fragment = new ShowFormDataFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_show_form_data, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        number = view.findViewById(R.id.number);
        dob = view.findViewById(R.id.dob);
        gender = view.findViewById(R.id.gender);
        aadharCard = view.findViewById(R.id.aadharCard);
        pancard = view.findViewById(R.id.panCard);
        voterCard = view.findViewById(R.id.voterCard);
        drivingLicence = view.findViewById(R.id.drivingLicence);
        state = view.findViewById(R.id.state);
        city = view.findViewById(R.id.city);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onResume() {
        super.onResume();

        Bundle bundle = getArguments();

        if (bundle != null) {
            name.setText(bundle.getString("name"));
            email.setText(bundle.getString("email"));
            number.setText(bundle.getString("number"));
            gender.setText(bundle.getString("gender"));
            dob.setText(bundle.getString("dob"));
            state.setText(bundle.getString("state"));
            city.setText(bundle.getString("city"));

            if (bundle.getBoolean("aadharCard"))
                aadharCard.post(new Runnable() {
                    @Override
                    public void run() {
                        aadharCard.setChecked(true);
                    }
                });
            if (bundle.getBoolean("panCard"))
                pancard.post(new Runnable() {
                @Override
                public void run() {
                    pancard.setChecked(true);
                }
            });
            if (bundle.getBoolean("voterCard"))
                voterCard.post(new Runnable() {
                @Override
                public void run() {
                    voterCard.setChecked(true);
                }
            });
            if (bundle.getBoolean("drivingLicence"))
                drivingLicence.post(new Runnable() {
                @Override
                public void run() {
                    drivingLicence.setChecked(true);
                }
            });
        }
    }

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
}
