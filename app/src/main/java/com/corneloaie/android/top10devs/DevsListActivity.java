package com.corneloaie.android.top10devs;

import android.support.v4.app.Fragment;

public class DevsListActivity extends FragmentActivity {

    @Override
    public Fragment createFragment() {
        return DevsListFragment.newInstance();
    }
}
