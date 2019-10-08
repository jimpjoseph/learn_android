package org.learning.locatr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class LocatorFragment extends Fragment {

    private ImageView mImageView;

    public static LocatorFragment newInstance() {
        return new LocatorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        View v = inflater.inflate(R.layout.fragment_locatr, container, false);

        mImageView = v.findViewById(R.id.image);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_locatr, menu);
    }
}
