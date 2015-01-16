package androidcourse.com.myPersonalAccountant.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Date;
import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.adapters.CategoryAdapter;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderRepository;
import androidcourse.com.myPersonalAccountant.util.ConstantsUtil;

public class AddOrderActivity extends ActionBarActivity {

    private  UserOrder userOrderObj;
    private Button cancelButton;
    private Button saveButton;
    private Button deleteButton;
    private EditText priceTag;
    private Date current_date;
    private int orderID;
    CategoryAdapter myAdapter;
    public static int selectdCell=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        orderID = getIntent().getIntExtra("orderID", -1);
        userOrderObj = (UserOrder) getIntent().getSerializableExtra("oderObject");

        selectdCell=0;

        if (userOrderObj==null) {
            userOrderObj = new UserOrder();
        } else {
            int category=userOrderObj.getCategory();
            if (category>0) {
                selectdCell=category;
            }
        }

        current_date = new Date();
        long long_date = getIntent().getLongExtra("dateLong", -1);
        if (long_date>0) {
            current_date.setTime(long_date);
            if (userOrderObj.getCreatedDate()==null) {
                userOrderObj.setCreatedDate(current_date);
            }
        }


        GridView gridview = (GridView) findViewById(R.id.gridview);
        myAdapter= new CategoryAdapter(this,selectdCell);
        gridview.setAdapter(myAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position!=selectdCell) {
                selectdCell=position;
                myAdapter.notifyDataSetChanged();
            }
            }
        });

        priceTag = (EditText) findViewById(R.id.priceTag);

        if (userOrderObj.getValue()!=null) {
            priceTag.setText(String.valueOf(userOrderObj.getValue()));
        }

        cancelButton= (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent returnIntent = new Intent();
            setResult(RESULT_CANCELED, returnIntent);
            finish();
            }
        });

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int change=1;
            if (priceTag.getText().toString().matches("") || priceTag.getText().toString().matches("0.0") ) {
                Toast.makeText(AddOrderActivity.this, "You did not enter a value", Toast.LENGTH_SHORT).show();
                change=0;
            } else {
                userOrderObj.setValue(Double.parseDouble(priceTag.getText().toString()));
            }

            if (current_date.toString()=="") {
                change=0;
                Toast.makeText(AddOrderActivity.this, "Error", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            if (selectdCell>-1) {
                userOrderObj.setCategory(selectdCell);
            }

            if (change>0) {
                OrderRepository categorydb = new OrderRepository(AddOrderActivity.this);
                if (orderID>-1) {
                    categorydb.update(userOrderObj);
                } else {
                    long newId=categorydb.insert(userOrderObj);
                    if (newId>-1) {
                        userOrderObj.setId((int)newId);
                    } else {
                        change=0;
                        Toast.makeText(AddOrderActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                categorydb.close();

                if (change>0) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("resultObj",userOrderObj);
                    returnIntent.putExtra("resultID",orderID);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
            }
            }
        });
        deleteButton= (Button) findViewById(R.id.deleteButton);
        if (orderID>-1) {
           deleteButton.setVisibility(View.VISIBLE);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderID>-1) {
                    OrderRepository categorydb = new OrderRepository(AddOrderActivity.this);
                    categorydb.delete(userOrderObj.getId());
                    categorydb.close();

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("action_taken",1);
                    returnIntent.putExtra("resultID",orderID);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                } else {
                    Toast.makeText(AddOrderActivity.this, "This can't be deleted", Toast.LENGTH_SHORT).show();
                }
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
