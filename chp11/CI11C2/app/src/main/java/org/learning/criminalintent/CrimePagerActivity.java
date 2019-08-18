package org.learning.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {


    private static final int PADDING = 16;
    private static final String EXTRA_CRIME_ID = "org.learning.criminalintent.crime_id";

    public static Intent newIntent(Context packageContent, UUID crimeId) {
        Intent intent = new Intent(packageContent,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    private Button mPrevButton;
    private Button mNextButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        mViewPager = findViewById(R.id.crime_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        mNextButton = findViewById(R.id.next_button);
        mPrevButton = findViewById(R.id.prev_button);

        // Start - Challenge 1
        mViewPager.setClipToPadding(false);
        mViewPager.setPadding(PADDING,0,PADDING,0);
        // End - Challenge 1


        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                // Check for boundry conditions and disable buttons accordingly
                if (i == 0) { // First element
                    mPrevButton.setEnabled(false);
                } else if (i == mCrimes.size() - 1) { //last element
                    mNextButton.setEnabled(false);
                }
                break;
            }
        }

        /* Boundry condtion.  # of items 0 or 1 */
        if (mCrimes.size() <= 1) {
            mNextButton.setEnabled(false);
            mPrevButton.setEnabled(false);
        }

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPos = mViewPager.getCurrentItem();
                if (currentPos >= mCrimes.size() - 1) {
                    return;
                }
                currentPos++;
                mViewPager.setCurrentItem(currentPos);
                if (currentPos >= mCrimes.size() - 1){
                    mNextButton.setEnabled(false);
                }
                mPrevButton.setEnabled(true);
            }
        });
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPos = mViewPager.getCurrentItem();
                if (currentPos == 0) {
                    return;
                }
                currentPos--;
                mViewPager.setCurrentItem(currentPos);
                if (currentPos == 0){
                    mPrevButton.setEnabled(false);
                }
                mNextButton.setEnabled(true);
            }
        });

    }


}
