package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

/**
 * Created by Ado on 1/10/2015.
 */
public class ExpenseCustomAdapter extends ArrayAdapter {
    private Integer[] iconListArray= ConstantsUtil.iconListArray;
    private List<UserOrder> expenseList;
    private Context mContext;
    public ExpenseCustomAdapter(Context context, int resource,List<UserOrder> expenseList) {
        super(context, resource, expenseList);
        mContext=context;
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
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expensecustomadapter_listitem, null);
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expensecustomadapter_listitem, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.expenseName);
        tvName.setText(String.valueOf(currentExpense.getValue()));
        ImageView imgView= (ImageView) convertView.findViewById(R.id.icon);
        int imgResource=0;
        if (currentExpense.getCategory()>0) {
            imgResource=currentExpense.getCategory();
        }
        imgView.setImageResource(iconListArray[imgResource]);

        return convertView;
    }
}
