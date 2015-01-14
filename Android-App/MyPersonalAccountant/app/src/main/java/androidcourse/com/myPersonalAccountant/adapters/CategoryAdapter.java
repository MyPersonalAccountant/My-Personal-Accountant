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
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

/**
 * Created by Ado on 1/12/2015.
 */
public class CategoryAdapter extends BaseAdapter {

    private Context mContext;
    private TypedArray iconList;
    private Integer[] iconListArray= ConstantsUtil.iconListArray;
    LayoutInflater inflater;
    public CategoryAdapter(Context c) {
        mContext = c;
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
//        View grid;
        if (convertView==null) {
//            grid = new View(mContext);
            convertView = inflater.inflate(R.layout.custom_cell_category, null);
        }

        ImageView imageView= (ImageView) convertView.findViewById(R.id.image);
        imageView.setImageResource(iconListArray[position]);

//        if (convertView == null) {  // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }

//        iconList.getd;
//        Log.e("Icon",String.valueOf(iconList.getDrawable(position)));
//        imageView.setImageResource(iconListArray[position]);
        return convertView;
    }
}
