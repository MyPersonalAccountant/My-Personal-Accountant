package androidcourse.com.myPersonalAccountant.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidcourse.com.myPersonalAccountant.R;


public class MainActivity extends FragmentActivity {

    public Date lastClickedDate=null;
    // change here
    private CalendarCustomFragment calendarFragment;

    private void setCustomResourceForDates(Date date) {
        Calendar cal = Calendar.getInstance();
        Date blueDate = null;

//        cal.add(Calendar.DATE,)
//        // Min date is last 7 days
//        cal.add(Calendar.DATE, -18);
//        Date blueDate = cal.getTime();
//
//        // Max date is next 7 days
//        cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 16);
//        Date greenDate = cal.getTime();


        if ( (calendarFragment != null) && (blueDate!=null)  ) {
            calendarFragment.setBackgroundResourceForDate(R.color.blue,blueDate);
//            calendarFragment.setBackgroundResourceForDate(R.color.green,
//                    greenDate);
            calendarFragment.setTextColorForDate(R.color.white, blueDate);
//            calendarFragment.setTextColorForDate(R.color.white, greenDate);
        }
    }

    private void setCustomResourceForDatesTest() {
        Calendar cal = Calendar.getInstance();

        // Min date is last 7 days
        cal.add(Calendar.DATE, -18);
        Date blueDate = cal.getTime();

        if ( (calendarFragment != null) && (blueDate!=null)  ) {
            calendarFragment.setBackgroundResourceForDate(R.color.blue,blueDate);
//            calendarFragment.setBackgroundResourceForDate(R.color.green,
//                    greenDate);
            calendarFragment.setTextColorForDate(R.color.white, blueDate);
//            calendarFragment.setTextColorForDate(R.color.white, greenDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup fragment
        calendarFragment = new CalendarCustomFragment();

        // Setup arguments
        // If Activity is created after rotation
        if (savedInstanceState != null) {
            calendarFragment.restoreStatesFromKey(savedInstanceState,
                    "CALENDAR_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CalendarCustomFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CalendarCustomFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CalendarCustomFragment.ENABLE_SWIPE, true);
            args.putBoolean(CalendarCustomFragment.SIX_WEEKS_IN_CALENDAR, true);

            calendarFragment.setArguments(args);
        }

        setCustomResourceForDatesTest();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, calendarFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();

                setCustomResourceForDates(date);
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                Toast.makeText(getApplicationContext(), text,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " + formatter.format(date),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (calendarFragment.getLeftArrowButton() != null) {
                    Toast.makeText(getApplicationContext(),
                            "View is created", Toast.LENGTH_SHORT)
                            .show();
                }
            }

        };

        // Setup Calendar
        calendarFragment.setCaldroidListener(listener);
    }

    /**
     * Save current states of the Calendar here
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (calendarFragment != null) {
            calendarFragment.saveStatesToKey(outState, "CALENDAR_SAVED_STATE");
        }
    }

}

