package com.example.user15.helloworldapplication.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user15.helloworldapplication.R;
import com.example.user15.helloworldapplication.activities.FormActivity;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText nameEditText, emailEditText, numberEditText;
    private EditText dobEditText;
    private RadioGroup radioGender;
    private RadioButton radioButton;
    private Spinner stateSpinner, citySpinner;
    private CheckBox aadharCardCheckBox, panCardCheckBox, voterCardCheckBox, drivingLicenceheckBox;
    private String name, email, number, gender, selectedState, selectedCity, dob;
    private Button submitForm;

    private OnFragmentInteractionListener mListener;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
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

        final View view = inflater.inflate(R.layout.fragment_form, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        numberEditText = view.findViewById(R.id.numberEditText);
        dobEditText = view.findViewById(R.id.dobEditText);
        radioGender = view.findViewById(R.id.radioGender);
        stateSpinner = view.findViewById(R.id.stateSpinner);
        citySpinner = view.findViewById(R.id.citySpinner);
        aadharCardCheckBox = view.findViewById(R.id.aadharCardCheckBox);
        panCardCheckBox = view.findViewById(R.id.panCardCheckBox);
        voterCardCheckBox = view.findViewById(R.id.voterCardCheckBox);
        drivingLicenceheckBox = view.findViewById(R.id.drivingLicenceheckBox);
        submitForm = view.findViewById(R.id.submitForm);

        final String[] state = getResources().getStringArray(R.array.state);
        final String[] gujraCity = getResources().getStringArray(R.array.gujrat_city);
        final String[] rajasthanCity = getResources().getStringArray(R.array.rajasthan_city);
        final String[] upCity = getResources().getStringArray(R.array.up_city);

        ArrayAdapter<String> stateArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, state);
        final ArrayAdapter<String> gujratCityArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, gujraCity);
        final ArrayAdapter<String> rajasthanCityArrayAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, rajasthanCity);
        final ArrayAdapter<String> upCityAdapter = new ArrayAdapter<>(getContext(), R.layout.simple_list_item, upCity);
        stateSpinner.setAdapter(stateArrayAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    citySpinner.setAdapter(gujratCityArrayAdapter);

                    selectedState = state[0];
                    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                selectedCity = gujraCity[0];
                            } else if (position == 1) {
                                selectedCity = gujraCity[1];
                            }else if (position == 2) {
                                selectedCity = gujraCity[2];
                            }else if (position == 3) {
                                selectedCity = gujraCity[3];
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else if (position == 1) {
                    citySpinner.setAdapter(rajasthanCityArrayAdapter);

                    selectedState = state[1];
                    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                selectedCity = rajasthanCity[0];
                            } else if (position == 1) {
                                selectedCity = rajasthanCity[1];
                            }else if (position == 2) {
                                selectedCity = rajasthanCity[2];
                            }else if (position == 3) {
                                selectedCity = rajasthanCity[3];
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                } else {
                    citySpinner.setAdapter(upCityAdapter);

                    selectedState = state[2];
                    citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                selectedCity = upCity[0];
                            } else if (position == 1) {
                                selectedCity = upCity[1];
                            }else if (position == 2) {
                                selectedCity = upCity[2];
                            }else if (position == 3) {
                                selectedCity = upCity[3];
                            }
                        }
                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        final Calendar calendar = Calendar.getInstance();

        dobEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        dobEditText.setText("" + year + "/" + (month + 1 ) + "/" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        submitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), FormActivity.class);
                name = nameEditText.getText().toString();
                email = emailEditText.getText().toString();
                number = numberEditText.getText().toString();
                dob = dobEditText.getText().toString();
                int selectedRadioButton = radioGender.getCheckedRadioButtonId();

                if ("".equals(name) || "".equals(email) || number.equals("") || dob.equals("Select date of birth") || selectedRadioButton == -1) {
                    Toast.makeText(getActivity(), "Please fill the above fields", Toast.LENGTH_SHORT).show();
                } else {

                    radioButton = view.findViewById(selectedRadioButton);
                    gender = radioButton.getText().toString();
                    if (aadharCardCheckBox.isChecked())
                        intent.putExtra("aadharCard", true);
                    else
                        intent.putExtra("aadharCard", false);

                    if (panCardCheckBox.isChecked())
                        intent.putExtra("panCard", true);
                    else
                        intent.putExtra("panCard", false);

                    if (voterCardCheckBox.isChecked())
                        intent.putExtra("voterCard", true);
                    else
                        intent.putExtra("voterCard", false);

                    if (drivingLicenceheckBox.isChecked())
                        intent.putExtra("drivingLicence", true);
                    else
                        intent.putExtra("drivingLicence", false);

                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("number", number);
                    intent.putExtra("dob", dob);
                    intent.putExtra("gender", gender);
                    intent.putExtra("state", selectedState);
                    intent.putExtra("city", selectedCity);
                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
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
