package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.Receiver.AlarmReceiver;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.GroupItem;

/**
 * Created by Kyle-PC on 5/3/2015.
 */
public class SetGroupStateTask extends AsyncTask {
    private DBHandler dbHandler;
    private int groupID;

    @Override
    protected GroupItem doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity)objects[0], null, null, 1);
        groupID = (int)objects[1];
        boolean stateToChangeTo = (boolean)objects[2];
        if(stateToChangeTo) {
            dbHandler.setGroupToOn(groupID);
        } else {
            dbHandler.setGroupToOff(groupID);
        }
        Activity thisContext = ((Activity)objects[0]);
        Intent alarmIntent = new Intent(thisContext, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(thisContext, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) thisContext.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Object[] parameters = { objects[0] };
        new GetNextAlarmTask().execute(parameters);
        // Don't ever think that this GroupItem does anything, it doesn't
        return new GroupItem();
    }
}
