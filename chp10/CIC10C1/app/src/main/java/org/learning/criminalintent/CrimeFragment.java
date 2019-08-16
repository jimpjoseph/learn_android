package org.learning.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class CrimeFragment extends Fragment {

    public static final String ARG_CRIME_ID = "crime_id";
    public static final String ARG_POSITION = "position";

    private int mPosition;
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeId, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);
        args.putInt(ARG_POSITION, position);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static int getPositionFromResult(Intent intent) {
        return intent.getIntExtra(ARG_POSITION,0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mPosition = getArguments().getInt(ARG_POSITION);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        if (mCrime == null){
            mCrime = new Crime();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
                setResult();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Do nothing
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);


        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
                setResult();
            }
        });
        return v;
    }

    private void setResult() {
        Intent intent = new Intent();
        intent.putExtra(ARG_POSITION, mPosition);
        getActivity().setResult(Activity.RESULT_OK, intent);
    }

}

