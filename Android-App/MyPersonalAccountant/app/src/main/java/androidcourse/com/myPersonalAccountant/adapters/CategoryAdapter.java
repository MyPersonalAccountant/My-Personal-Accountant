package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidcourse.com.myPersonalAccountant.R;

/**
 * Created by Ado on 1/12/2015.
 */
public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private TypedArray iconList;

    public CategoryAdapter(Context c) {
        mContext = c;
        Resources res = c.getResources();
        iconList = res.obtainTypedArray(R.array.icon_pack_names);
    }

    @Override
    public int getCount() {
        return iconList.length();
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return iconList.getIndex(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(iconList.getIndex(position));
        return imageView;
    }
}
