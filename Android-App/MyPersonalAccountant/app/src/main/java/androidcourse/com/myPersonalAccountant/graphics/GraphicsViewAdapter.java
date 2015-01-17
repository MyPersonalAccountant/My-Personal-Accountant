package androidcourse.com.myPersonalAccountant.graphics;

import java.util.Date;
import java.util.List;

public interface GraphicsViewAdapter{
    /**
     * should return sorted x positions for the bars.
     */
    public List<Date> getXValues();
    public List<Float> getYValues();
    public void notifyDataSetChanged();
}
