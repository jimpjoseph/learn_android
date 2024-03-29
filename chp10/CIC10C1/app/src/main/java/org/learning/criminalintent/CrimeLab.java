package org.learning.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimes;
    private Map<UUID, Integer> mCrimesMap;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        mCrimesMap = new HashMap<>();

        for (int i = 0; i < 100; i++) {
            Crime c = new Crime();
            c.setTitle("Crime # "+ i);
            c.setSolved(i % 2 == 0);
            mCrimes.add(c);
            mCrimesMap.put(c.getId(),i);
        }
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        if (mCrimesMap.containsKey(id)){
            return mCrimes.get(mCrimesMap.get(id));
        }
        return null;
    }
}
