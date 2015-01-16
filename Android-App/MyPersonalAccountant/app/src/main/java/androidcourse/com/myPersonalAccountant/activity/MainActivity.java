package androidcourse.com.myPersonalAccountant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.Category;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.CategoryRepository;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderRepository;


public class MainActivity extends FragmentActivity {
    private  HashMap<String,List<UserOrder>> categoriesList;

    public Date lastClickedDate=null;
    // change here
    private CalendarCustomFragment calendarFragment;

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

//        MainActivity.this.setTitle("Test");
//        ((MainActivity) getActivity()).setActionBarTitle(YOUR_TITLE);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        // Setup fragment
        calendarFragment = new CalendarCustomFragment();
        if (calendarFragment!=null) {
            this.categoriesList=loadData();
            calendarFragment.setOrders(categoriesList);
        }

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
                calendarFragment.clearSelectedDates();

                Intent i = new Intent(MainActivity.this, ExpenseActivity.class);
                i.putExtra("Current_Date", date.getTime());
                startActivity(i);
            }

            @Override
            public void onChangeMonth(int month, int year) {
//                String text = "month: " + month + " year: " + year;
//                Toast.makeText(getApplicationContext(), text,
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
//                Toast.makeText(getApplicationContext(),
//                        "Long click " + formatter.format(date),
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (calendarFragment.getLeftArrowButton() != null) {
//                    Toast.makeText(getApplicationContext(),
//                            "View is created", Toast.LENGTH_SHORT)
//                            .show();
                }
            }
        };

        // Setup Calendar
        calendarFragment.setCaldroidListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (calendarFragment!=null) {
            this.categoriesList=loadData();
            calendarFragment.setOrders(categoriesList);
        }
    }

    public HashMap<String,List<UserOrder>> loadData() {
        OrderRepository categorydb = new OrderRepository(this);
        HashMap<String,List<UserOrder>> categoriesList= null;
        categoriesList=categorydb.getGroupAll();
        categorydb.close();
        return categoriesList;
    }

    /**
     * Save current states of the Calendar here
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);

        if (calendarFragment != null) {
            calendarFragment.saveStatesToKey(outState, "CALENDAR_SAVED_STATE");
        }
    }

}

