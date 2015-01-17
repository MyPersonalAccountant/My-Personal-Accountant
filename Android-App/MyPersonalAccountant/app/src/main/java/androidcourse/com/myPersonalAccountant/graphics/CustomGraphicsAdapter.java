package androidcourse.com.myPersonalAccountant.graphics;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderRepository;

/**
 * Created by alexandrageorgieva on 1/17/15.
 */
public class CustomGraphicsAdapter implements GraphicsViewAdapter {

    private List<Date> dates = new ArrayList<Date>();
    private List<Float> expenses = new ArrayList<Float>();
    Context context;
    private int month;
    private  int year;

    @Override
    public List<Float> getYValues() {
        ArrayList<Float> arr = new ArrayList<Float>();
        arr.add(10.0f);
        arr.add(30f);
        arr.add(5f);
        return  expenses;
    }

    @Override
    public List<Date> getXValues() {
        ArrayList<Date> arr = new ArrayList<Date>();
        arr.add(new Date(2015,1,27));
        arr.add(new Date(2015,1,5));
        arr.add(new Date());
        return dates;
    }

    @Override
    public void notifyDataSetChanged() {

    }

    public CustomGraphicsAdapter(Context ctx) {
        this.context = ctx;
        Date today = new Date();
        month = today.getMonth();
        year = today.getYear()+1900;
        getDataFor(month, year);
    }

    public void getDataFor(int month, int year){
        OrderRepository categorydb = new OrderRepository(context);
        Calendar cal = Calendar.getInstance();
        int max = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        List<UserOrder> orders;
        for (int i = 1; i <= max; i++){
            cal.set(year, month, i);
            Date date = new Date(cal.getTimeInMillis());
            orders = categorydb.getAllFromDate(date);
            dates.add(date);
            float totalExpense = 0;
            for (UserOrder order : orders) {
                totalExpense += order.getValue();
            }

             expenses.add(totalExpense);
        }

        categorydb.close();
    }

}
