package com.ninjawebzen.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by slavkurochkin on 1/7/17.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}