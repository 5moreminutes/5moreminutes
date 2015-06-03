package fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;

import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.R;
import fivemoreminutes.cs499.cs.csupomona.edu.fivemoreminutes.adapters.DrillDownAlarmAdapter;

/**
 * Created by Calvin on 4/29/2015.
 */
public class DrillDownAlarmActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int defaultValue = 0;
        Log.e("PASSED_GROUP_ID", ""+intent.getIntExtra("GROUP_ID", defaultValue));

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new DrillDownAlarmAdapter(getSupportFragmentManager(), intent.getIntExtra("GROUP_ID", defaultValue)));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabsStrip.setTextColor(Color.parseColor("#FFFFFF"));
        tabsStrip.setIndicatorColor(getResources().getColor(R.color.tab_background));
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);


    }
}
