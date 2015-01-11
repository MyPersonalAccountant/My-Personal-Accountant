package androidcourse.com.myPersonalAccountant.activity;

import android.util.Log;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.Category;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.CategoryRepository;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderRepository;

/**
 * Created by Ado on 12/31/2014.
 */
public class CalendarCustomFragment extends CaldroidFragment {
    private HashMap<String,List<UserOrder>> orders;

    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub

        extraData.put("expenses",orders);
        return new CalendarCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
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
