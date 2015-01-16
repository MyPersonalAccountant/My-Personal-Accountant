package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import hirondelle.date4j.DateTime;

/**
 * Created by Ado on 12/31/2014.
 */
public class CalendarCustomAdapter extends CaldroidGridAdapter {
    private double customMoney=200000;
    Date firstExpenseDate;
    Calendar getDateCalendar;
    int year_start=-1;
    int month_start=-1;
    int day_start=-1;
    HashMap<String,List<UserOrder>> allOrders;
    public CalendarCustomAdapter (Context context, int month, int year,
                                  HashMap<String, Object> calendarData,
                                  HashMap<String, Object> extraData) {
        super(context, month, year, calendarData, extraData);
        this.allOrders= (HashMap<String,List<UserOrder>>)extraData.get("expenses");
        this.firstExpenseDate= (Date) extraData.get("firstExpense");
        if (this.firstExpenseDate!=null) {
            getDateCalendar=Calendar.getInstance();
            getDateCalendar.setTime(this.firstExpenseDate);
            this.year_start = getDateCalendar.get(Calendar.YEAR);
            this.month_start = getDateCalendar.get(Calendar.MONTH);
            this.day_start = getDateCalendar.get(Calendar.DAY_OF_MONTH);
        }

        Log.e("STARTDAY",String.valueOf(day_start));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cellView = convertView;

        // For reuse
        if (convertView == null) {
            cellView = inflater.inflate(R.layout.custom_cell, null);
        }

        int topPadding = cellView.getPaddingTop();
        int leftPadding = cellView.getPaddingLeft();
        int bottomPadding = cellView.getPaddingBottom();
        int rightPadding = cellView.getPaddingRight();

        TextView tv1 = (TextView) cellView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) cellView.findViewById(R.id.tv2);
        TextView tv3 = (TextView) cellView.findViewById(R.id.tv3);

        tv1.setTextColor(Color.BLACK);

        // Get dateTime of this cell
        DateTime dateTime = this.datetimeList.get(position);
        Resources resources = context.getResources();

        // Set color of the dates in previous / next month
        if (dateTime.getMonth() != month) {
            tv1.setTextColor(resources.getColor(com.caldroid.R.color.caldroid_darker_gray));
        }

        boolean shouldResetDiabledView = false;
        boolean shouldResetSelectedView = false;

        // Customize for disabled dates and date outside min/max dates
        if ((minDateTime != null && dateTime.lt(minDateTime))
                || (maxDateTime != null && dateTime.gt(maxDateTime))
                || (disableDates != null && disableDates.indexOf(dateTime) != -1)) {

            tv1.setTextColor(CaldroidFragment.disabledTextColor);
            if (CaldroidFragment.disabledBackgroundDrawable == -1) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.disable_cell);
            } else {
                cellView.setBackgroundResource(CaldroidFragment.disabledBackgroundDrawable);
            }

            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border_gray_bg);
            }

        } else {
            shouldResetDiabledView = true;
        }

        // Customize for selected dates
        if (selectedDates != null && selectedDates.indexOf(dateTime) != -1) {
            if (CaldroidFragment.selectedBackgroundDrawable != -1) {
                cellView.setBackgroundResource(CaldroidFragment.selectedBackgroundDrawable);
            } else {
                cellView.setBackgroundColor(resources
                        .getColor(com.caldroid.R.color.caldroid_sky_blue));
            }

            tv1.setTextColor(CaldroidFragment.selectedTextColor);

        } else {
            shouldResetSelectedView = true;
        }

        if (shouldResetDiabledView && shouldResetSelectedView) {
            // Customize for today
            if (dateTime.equals(getToday())) {
                cellView.setBackgroundResource(com.caldroid.R.drawable.red_border);
            } else {
                cellView.setBackgroundResource(com.caldroid.R.drawable.cell_bg);
            }
        }

        if (getDateCalendar!=null) {
            int day_now = dateTime.getDay();
            int month_now = dateTime.getMonth();
            int year_now = dateTime.getYear();

            if (day_now==1) {
                customMoney=200000;
            }
        }

        // set Date;
        tv1.setText("" + dateTime.getDay());
        Double spend=0.0;
        if (allOrders.size()>0) {
            Date ddate = new Date(dateTime.getMilliseconds(TimeZone.getTimeZone("GMT-8")));
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            List<UserOrder> dateValues = allOrders.get(formatter.format(ddate));
            if (dateValues!=null) {
                for (int i = 0; i < dateValues.size(); i++) {
                    spend+=dateValues.get(i).getValue();
                }
                if (spend>0) {
                    tv2.setText("-"+spend.toString());
                    customMoney-=spend;
                } else {
                    tv2.setText("");
                }
            } else {
                tv2.setText("");
            }
        }
        tv3.setText(String.valueOf(customMoney));

        // Somehow after setBackgroundResource, the padding collapse.
        // This is to recover the padding
        cellView.setPadding(leftPadding, topPadding, rightPadding,
                bottomPadding);

        // Set custom color if required
        setCustomResources(dateTime, cellView, tv1);
        setCustomResources(dateTime, cellView, tv2);
        setCustomResources(dateTime, cellView, tv3);

        return cellView;
    }

}

