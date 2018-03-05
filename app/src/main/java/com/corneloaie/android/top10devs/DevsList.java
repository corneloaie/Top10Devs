package com.corneloaie.android.top10devs;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cornel-PC on 05/03/2018.
 */

public class DevsList {
    private static DevsList sDevsList;
    private List<Developer> devsList;

    private DevsList(Context context) {
        devsList = new ArrayList<>();
    }

    public static DevsList get(Context context) {
        if (sDevsList == null) {
            sDevsList = new DevsList(context);
        }
        return sDevsList;
    }

    public List<Developer> getDevs() {
        return devsList;
    }

    public Developer getDeveloper(int userID) {

        return devsList.get(devsList.indexOf(new Developer(userID)));
    }
}
