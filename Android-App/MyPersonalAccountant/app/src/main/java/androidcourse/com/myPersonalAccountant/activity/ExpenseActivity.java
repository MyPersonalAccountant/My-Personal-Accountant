package androidcourse.com.myPersonalAccountant.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import androidcourse.com.myPersonalAccountant.R;
import androidcourse.com.myPersonalAccountant.adapters.CategoryAdapter;
import androidcourse.com.myPersonalAccountant.adapters.ExpenseCustomAdapter;
import androidcourse.com.myPersonalAccountant.entity.Expense;
import androidcourse.com.myPersonalAccountant.entity.UserOrder;
import androidcourse.com.myPersonalAccountant.sqlhelperImpl.OrderRepository;

public class ExpenseActivity extends ActionBarActivity implements OnItemClickListener {

    private ListView listView;
    private List<UserOrder> categoriesList;
    private ExpenseCustomAdapter adapterExpenseFromDate;
    private Date current_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        current_date = new Date();
        long long_date = getIntent().getLongExtra("Current_Date", -1);

        if (long_date>-1) {
            current_date.setTime(long_date);
            OrderRepository categorydb = new OrderRepository(this);
            categoriesList=categorydb.getAllFromDate(current_date);
            categorydb.close();
        }
        listView = (ListView) findViewById(R.id.listview);

        adapterExpenseFromDate = new ExpenseCustomAdapter(this,R.layout.expensecustomadapter_listitem, categoriesList);
        listView.setAdapter(adapterExpenseFromDate);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ExpenseActivity.this, AddOrderActivity.class);
                i.putExtra("orderID",categoriesList.get(position).getId());
                i.putExtra("dateLong",current_date.getTime());
                i.putExtra("oderObject",categoriesList.get(position));
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                UserOrder userOrderObj = (UserOrder) data.getSerializableExtra("resultObj");
                int orderID = data.getIntExtra("resultID",-1);
                int actionTaken = data.getIntExtra("action_taken",-1);
                if ( actionTaken<0 )  {
                    Log.e("imaKategoriq",String.valueOf(userOrderObj.getCategory()));
                    if (orderID>-1) {
                        for (int i = 0; i < categoriesList.size(); i++) {
                            UserOrder userObj = categoriesList.get(i);
                            int oldID=userObj.getId();
                            int getId=userOrderObj.getId();
                            if (oldID==getId) {
                                categoriesList.set(i,userOrderObj);
                            }
                        }
                    } else {
                        categoriesList.add(userOrderObj);
                    }
                    adapterExpenseFromDate.notifyDataSetChanged();
                } else {
                    if ( (actionTaken>-1) && (orderID>-1) ) {
                        for (int i = 0; i < categoriesList.size(); i++) {
                            UserOrder userObj = categoriesList.get(i);
                            if (userObj.getId()==orderID) {
                                categoriesList.remove(i);
                            }
                        }
                        adapterExpenseFromDate.notifyDataSetChanged();
                    }
                }
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_expense) {
            Intent i = new Intent(ExpenseActivity.this, AddOrderActivity.class);
            i.putExtra("dateLong",current_date.getTime());
            startActivityForResult(i,1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(ExpenseActivity.this,"bla "+ String.valueOf(categoriesList.get(position).getId()),Toast.LENGTH_SHORT);
    }
}
