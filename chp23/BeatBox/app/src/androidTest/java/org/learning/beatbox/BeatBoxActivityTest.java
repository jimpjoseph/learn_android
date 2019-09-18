package org.learning.beatbox;



import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsAnything.anything;



@RunWith(AndroidJUnit4ClassRunner.class)
public class BeatBoxActivityTest {
    @Rule
    public ActivityTestRule<BeatBoxActivity> mActivityTestRule = new ActivityTestRule<>(BeatBoxActivity.class);


    @Test
    public void showFirstFileName() {
        onView(withText("65_cjipie")).check(matches(anything()));
    }

}
