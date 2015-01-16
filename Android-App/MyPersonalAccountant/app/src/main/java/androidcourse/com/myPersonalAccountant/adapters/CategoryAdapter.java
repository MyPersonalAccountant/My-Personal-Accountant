package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.activity.AddOrderActivity;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

/**
 * Created by Ado on 1/12/2015.
 */
public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private int selectedElement;
    private TypedArray iconList;
    private Integer[] iconListArray= ConstantsUtil.iconListArray;
    LayoutInflater inflater;
    public CategoryAdapter(Context c,int selectedElement) {
        mContext = c;
        this.selectedElement=selectedElement;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return iconListArray.length;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return iconListArray[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.custom_cell_category, null);
        }

        if (position== AddOrderActivity.selectdCell) {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.lightblue));
        } else {
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        ImageView imageView= (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageResource(iconListArray[position]);

        return convertView;
    }
}
