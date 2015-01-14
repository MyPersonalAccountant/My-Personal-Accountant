package androidcourse.com.myPersonalAccountant.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.adapters.CategoryAdapter;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

public class AddOrderActivity extends ActionBarActivity {

//    private Integer[] iconListArray = ConstantsUtil.iconListArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        int orderID = getIntent().getIntExtra("orderID", -1);
        UserOrder userOrderObj = (UserOrder) getIntent().getSerializableExtra("oderObject");

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new CategoryAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddOrderActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        if (userOrderObj!=null) {
            EditText priceTag = (EditText) findViewById(R.id.priceTag);
            priceTag.setText(String.valueOf(userOrderObj.getValue()));
        }

        Button cancelButton= (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_order, menu);
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
