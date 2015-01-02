package androidcourse.com.myPersonalAccountant.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.List;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelper.SqlRepository;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderSQLHelper;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


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


    @Override
    protected void onResume() {

        setContentView(R.layout.activity_main);

        Button testButton = (Button) findViewById(R.id.test);
        testButton.setOnClickListener(new Event(this, testButton));
        super.onResume();
    }

    class Event implements View.OnClickListener {
        Context ctx;
        Button btn;

        public Event(Context ctx, Button btn) {
            this.ctx = ctx;
            this.btn = btn;
        }

        @Override
        public void onClick(View v) {
            SqlRepository<UserOrder> item = new OrderSQLHelper(ctx);

            UserOrder order = new UserOrder();
            order.setName(" Fahri ");
            long num = item.insert(order);

            List<UserOrder> orderList = item.getAll();

            String text = "";
            for (UserOrder o: orderList){
                text += o.getName();
            }

            btn.setText(text);
            btn.invalidate();
        }
    }
}
