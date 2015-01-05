package androidcourse.com.myPersonalAccountant.activity;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderSQLHelper;

/**
 * Created by Ado on 12/31/2014.
 */
public class CalendarCustomFragment extends CaldroidFragment {
    private List<UserOrder> orders = null;
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub

        // GET ORDERS
        OrderSQLHelper db = new OrderSQLHelper(getActivity());
        orders = db.getAll();
        db.close();

        return new CalendarCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData, orders);
    }

    @Override
    protected int getGridViewRes() {
        return R.layout.custom_grid;
    }
}
