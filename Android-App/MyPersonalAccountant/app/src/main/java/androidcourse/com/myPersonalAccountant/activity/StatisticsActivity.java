package androidcourse.com.myPersonalAccountant.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.graphics.CustomGraphicsAdapter;
import androidcourse.com.myPersonalAccountant.graphics.GraphicsBarView;
import androidcourse.com.myPersonalAccountant.graphics.GraphicsViewAdapter;

public class StatisticsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        GraphicsBarView graphics = (GraphicsBarView) findViewById(R.id.statisticsGraph);
        graphics.set_adapter(new CustomGraphicsAdapter(this));

        TextView textView = (TextView) findViewById(R.id.statistics_date_textView);
        Format formatter = new SimpleDateFormat("MM-yyyy");
        textView.setText(formatter.format(new Date()));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_statistics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
