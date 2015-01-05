package androidcourse.com.myPersonalAccountant.activity;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;

/**
 * Created by Ado on 12/31/2014.
 */
public class CalendarCustomFragment extends CaldroidFragment {
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub

        return new CalendarCustomAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

    @Override
    protected int getGridViewRes() {
        return R.layout.custom_grid;
    }
}
