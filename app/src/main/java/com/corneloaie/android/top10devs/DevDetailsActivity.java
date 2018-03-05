package com.corneloaie.android.top10devs;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by Cornel-PC on 05/03/2018.
 */

public class DevDetailsActivity extends FragmentActivity {
    private static final String EXTRA_USER_ID =
            "com.corneloaie.android.top10devs.userID";

    public static Intent newIntent(Context packageContext, int userID) {
        Intent intent = new Intent(packageContext, DevDetailsActivity.class);
        intent.putExtra(EXTRA_USER_ID, userID);
        return intent;


    }

    @Override
    public Fragment createFragment() {

        return DevDetailsFragment.newInstance(getIntent().getIntExtra(EXTRA_USER_ID, 0));
    }
}
