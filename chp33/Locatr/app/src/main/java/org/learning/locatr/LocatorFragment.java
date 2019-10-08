package org.learning.locatr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LocatorFragment extends Fragment {
    public static LocatorFragment newInstance() {
        return new LocatorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        View v = inflater.inflate(R.layout.fragment_locatr, container, false);
        return v;
    }
}
