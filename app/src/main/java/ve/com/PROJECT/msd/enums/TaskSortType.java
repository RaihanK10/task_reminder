package ve.com.PROJECT.msd.enums;

import android.content.Context;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import ve.com.PROJECT.msd.R;



public enum TaskSortType {
    DATE(R.string.task_sort_type_date, R.string.task_sort_type_date_message),
    PLACE(R.string.task_sort_type_location, R.string.task_sort_type_location_message);

    private @StringRes
    int friendlyNameRes;
    int friendlyMessageRes;

    TaskSortType(@StringRes int friendlyNameRes, @StringRes int friendlyMessageRes) {
        this.friendlyNameRes = friendlyNameRes;
        this.friendlyMessageRes = friendlyMessageRes;

    }

    public int getFriendlyNameRes() {
        return friendlyNameRes;
    }

    public int getFriendlyMessageRes() {
        return friendlyMessageRes;
    }

    public static List<String> getFriendlyValues(Context context) {
        List<String> friendlyValues = new ArrayList<>();
        for (TaskSortType tst : values()) {
            friendlyValues.add(context.getResources().getString(tst.friendlyNameRes));
        }
        return friendlyValues;
    }
}
