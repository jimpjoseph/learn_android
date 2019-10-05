package org.learning.draganddraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class DragAndDropFragment extends Fragment {

    public static DragAndDropFragment newInstance() {
        return new DragAndDropFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancestate) {
        View v = inflater.inflate(R.layout.fragment_drag_and_drop, container, false);
        return v;
    }
}
