package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;

/**
 * Created by Ado on 1/10/2015.
 */
public class ExpenseCustomAdapter extends ArrayAdapter {
    private List<UserOrder> expenseList;
    public ExpenseCustomAdapter(Context context, int resource,List<UserOrder> expenseList) {
        super(context, resource, expenseList);
        this.expenseList=expenseList;
    }

    public int getCount() {
        return this.expenseList.size();
    }

    public UserOrder getItem(int position) {
        return this.expenseList.get(position);
    }

    public long getItemId(int position) {
        return this.expenseList.get(position).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserOrder currentExpense = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expensecustomadapter_listitem, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.expenseName);
        tvName.setText(String.valueOf(currentExpense.getValue()));

        return convertView;
    }
}
