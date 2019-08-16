package org.learning.criminalintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    private static final String EXTRA_CRIME_ID = "org.learning.criminalintent.crime_id";
    private static final String EXTRA_POSTION = "org.learning.criminalintent.position";

    public static Intent newIntent(Context packageContent, UUID crimeId, int position) {
        Intent intent = new Intent(packageContent,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        intent.putExtra(EXTRA_POSTION, position);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        int position = getIntent().getIntExtra(EXTRA_POSTION, 0);
        return CrimeFragment.newInstance(crimeId, position);
    }

}
