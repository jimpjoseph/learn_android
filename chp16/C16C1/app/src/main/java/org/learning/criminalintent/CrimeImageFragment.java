package org.learning.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.util.UUID;

public class CrimeImageFragment extends DialogFragment {

    private static final String ARG_PHOTO = "PHOTO";
    private ImageView mCrimeDetail;

    public static CrimeImageFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, file);

        CrimeImageFragment fragment = new CrimeImageFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        File file = (File)getArguments().getSerializable(ARG_PHOTO);

        View v = LayoutInflater.from(getActivity()).
                inflate(R.layout.crime_detail, null);

        mCrimeDetail = v.findViewById(R.id.crime_image);

        Bitmap bitmap = PictureUtils.getScaledBitmap(file.getPath(),getActivity());
        mCrimeDetail.setImageBitmap(bitmap);
        return new AlertDialog.Builder(getActivity()).
                setView(v).
                setTitle("Crime Image").setPositiveButton(android.R.string.ok,
                null).create();
    }
}
