package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.model;


import android.app.Activity;
import android.os.AsyncTask;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.AlarmItem;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.data.CurrentNextAlarm;

/**
 * Created by Calvin on 5/29/2015.
 */
public class GetNextAlarmTask extends AsyncTask {
    private DBHandler dbHandler;


    @Override
    protected AlarmItem doInBackground(Object[] objects) {
        dbHandler = new DBHandler((Activity) objects[0], null, null, 1);
        AlarmItem fromDB = dbHandler.getNextAlarm();
        //first If for when there are no alarms.
        if(fromDB != null) {
            CurrentNextAlarm.setCurrentNextAlarm(fromDB, (Activity) objects[0]);
            CurrentNextAlarm.getCurrentNextAlarm().setMinute(CurrentNextAlarm.getCurrentNextAlarm().getMinute() + 1);
        }
        return fromDB;
    }
}
