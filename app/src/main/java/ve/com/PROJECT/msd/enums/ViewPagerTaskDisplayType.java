package ve.com.PROJECT.msd.enums;

import android.content.Context;
import android.support.annotation.StringRes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ve.com.PROJECT.msd.R;


public enum ViewPagerTaskDisplayType implements Serializable {
    UNPROGRAMMED(R.string.activity_home_tab_unprogrammed),
    PROGRAMMED(R.string.activity_home_tab_programmed),
    DONE(R.string.activity_home_tab_done);

    private @StringRes
    int friendlyNameRes;

    ViewPagerTaskDisplayType(@StringRes int friendlyNameRes) {
        this.friendlyNameRes = friendlyNameRes;

    }

    public int getFriendlyNameRes() {
        return friendlyNameRes;
    }

    public static List<String> getFriendlyValues(Context context) {
        List<String> friendlyValues = new ArrayList<>();
        for (ViewPagerTaskDisplayType ts : values()) {
            friendlyValues.add(context.getResources().getString(ts.friendlyNameRes));
        }
        return friendlyValues;
    }

}
