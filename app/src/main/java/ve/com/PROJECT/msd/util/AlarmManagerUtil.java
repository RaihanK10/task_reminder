package ve.com.PROJECT.msd.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import ve.com.PROJECT.msd.app.services.TriggerTaskNotificationReceiver;
import ve.com.PROJECT.msd.database.RemindyDAO;
import ve.com.PROJECT.msd.exception.CouldNotGetDataException;
import ve.com.PROJECT.msd.viewmodel.TaskTriggerViewModel;



public class AlarmManagerUtil {

    //CONST
    private static final String TAG = AlarmManagerUtil.class.getSimpleName();

    public static void updateAlarms(Context context) {
        Log.d(TAG, "Updating alarms.");

        List<Integer> triggeredTasks = SharedPreferenceUtil.getTriggeredTaskList(context);
        Log.d(TAG, "TriggeredTask IDs = " + TextUtils.join(",", triggeredTasks));

        //Get next task to trigger
        TaskTriggerViewModel task;
        try {
            task = new RemindyDAO(context).getNextTaskToTrigger(triggeredTasks);
        } catch (CouldNotGetDataException e ) {
            task = null;
        }

        if(task != null) {
            Log.d(TAG, "Got Task ID " + task.getTask().getId() + ". Title:" + task.getTriggerDateWithTime());

            int triggerMinutesBeforeNotification = SharedPreferenceUtil.getTriggerMinutesBeforeNotification(context).getMinutes();

            Calendar triggerTime = Calendar.getInstance();
            CalendarUtil.copyCalendar(task.getTriggerDateWithTime(), triggerTime);
            triggerTime.add(Calendar.MINUTE, -1);

            //Build intent
            Intent triggerIntent =  new Intent(context, TriggerTaskNotificationReceiver.class);
            triggerIntent.putExtra(TriggerTaskNotificationReceiver.TASK_ID_EXTRA, task.getTask().getId());

                Log.d(TAG, "Programming alarm for " + triggerTime.getTime());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, triggerIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                manager.set(AlarmManager.RTC_WAKEUP,triggerTime.getTimeInMillis(), pendingIntent);
            }
        Toast.makeText(context, "Alarms updated!", Toast.LENGTH_SHORT).show();

    }
}
