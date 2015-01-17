package androidcourse.com.myPersonalAccountant.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.HashMap;
import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.adapters.CalendarCustomAdapter;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;

/**
 * Created by Ado on 12/31/2014.
 */
public class CalendarCustomFragment extends CaldroidFragment {
    private HashMap<String,List<UserOrder>> orders;
    private CalendarCustomAdapter customAdapter;
    private int this_month;
    private int this_year;

    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        this.this_month=month;
        this.this_year=year;
        // TODO Auto-generated method stub

        extraData.put("expenses",orders);
        if ( (orders!=null) && (!orders.isEmpty()) ) {
            extraData.put("firstExpense",orders.get("startExpenseDate").get(0).getCreatedDate());
        }
        customAdapter = new CalendarCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
        return customAdapter;
    }

    public void setOrders(HashMap<String,List<UserOrder>> orders) {
        this.orders = orders;
    }

    public HashMap<String,List<UserOrder>> getOrders() {
        return orders;
    }

    @Override
    protected int getGridViewRes() {
        return R.layout.custom_grid;
    }
}
