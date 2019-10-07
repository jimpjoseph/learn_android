package org.learning.sunset;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SunsetFragment extends Fragment {
    public static SunsetFragment newInstance() {
        return new SunsetFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        View v = inflater.inflate(R.layout.fragment_sunset, container, false);
        return v;
    }

}
