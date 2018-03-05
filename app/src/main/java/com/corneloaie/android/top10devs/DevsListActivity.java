package com.corneloaie.android.top10devs;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class DevsListActivity extends FragmentActivity implements DevsListFragment.Callbacks {

    @Override
    public Fragment createFragment() {
        return DevsListFragment.newInstance();
    }

    @Override
    public void onDevSelected(Developer developer) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = DevDetailsActivity.newIntent(this, developer.getUserID());
            startActivity(intent);
        } else {
            Fragment newDetail = DevDetailsFragment.newInstance(developer.getUserID());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
