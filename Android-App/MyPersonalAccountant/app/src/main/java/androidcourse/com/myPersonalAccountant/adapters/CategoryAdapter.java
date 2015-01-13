package androidcourse.com.myPersonalAccountant.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
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
    private Integer[] iconListArray={R.drawable.banana,R.drawable.beer_bottle,R.drawable.beer_glass,R.drawable.birthday_cake,R.drawable.bread,
            R.drawable.bunch_ingredients,R.drawable.carrot,R.drawable.cheese,R.drawable.cinnamon_roll,R.drawable.cocktail,
            R.drawable.coffee,R.drawable.cooker,R.drawable.cooker_hood,R.drawable.cookies,R.drawable.cooking_pot,
            R.drawable.corkscrew,R.drawable.cup,R.drawable.cupcake};

    public CategoryAdapter(Context c) {
        mContext = c;
//        Resources res = c.getResources();
//        iconList = res.obtainTypedArray(R.array.icon_pack_names);
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
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

//        iconList.getd;
//        Log.e("Icon",String.valueOf(iconList.getDrawable(position)));
        imageView.setImageResource(iconListArray[position]);
        return imageView;
    }
}
