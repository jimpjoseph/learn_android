package org.learning.draganddraw;

import androidx.fragment.app.Fragment;

public class DragAndDropActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return DragAndDropFragment.newInstance();
    }

}
