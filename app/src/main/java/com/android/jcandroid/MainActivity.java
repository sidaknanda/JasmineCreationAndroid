package com.android.jcandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.jcandroid.Database.DAO;
import com.android.jcandroid.Model.CustomerModel;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private CustomerListAdapter adapter;
    private ArrayList<CustomerModel> customersList;
    private RecyclerView recyclerView;
    private TextView textViewNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInit();
        getDataFromLocalDb();
        setupList();
    }

    private void setupInit() {
        setContentView(R.layout.activity_main);
        textViewNoData = (TextView) findViewById(R.id.textViewNoData);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getDataFromLocalDb() {
        customersList = null;
        try {
            customersList = DAO.getAllCustomers(this);
            Collections.sort(customersList, CustomerModel.customerNameComparator);
        } catch (Exception e) {
            e.printStackTrace();
            Utility.showToast(this, "Error\nRestart the application");
        }
    }

    private void setupList() {
        if (customersList != null) {
            adapter = new CustomerListAdapter(this, customersList);
            recyclerView.setAdapter(adapter);
            if (customersList.size() != 0) {
                textViewNoData.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, CustomerDetailActivity.class));
                break;
            case R.id.action_sync_in:
                startActivity(new Intent(this, CloudSyncInActivity.class));
                break;
            case R.id.action_sync_out:
                startActivity(new Intent(this, CloudSyncOutActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}