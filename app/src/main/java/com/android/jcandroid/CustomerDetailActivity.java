package com.android.jcandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.jcandroid.Database.DAO;
import com.android.jcandroid.Model.CustomerModel;

public class CustomerDetailActivity extends AppCompatActivity {

    private CustomerModel customer;
    private FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail);

        try {
            customer = getIntent().getParcelableExtra(Utility.SELECTED_CUSTOMER);
        } catch (Exception e) {
            e.printStackTrace();
            customer = null;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (customer != null) {
            CustomerViewFragment viewFragment = new CustomerViewFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(Utility.SELECTED_CUSTOMER, customer);
            viewFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.frame_layout, viewFragment, Utility.VIEW_FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.replace(R.id.frame_layout, new CustomerAddUpdateFragment(), Utility.ADD_UPDATE_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (customer != null) {
            getMenuInflater().inflate(R.menu.menu_customer, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CustomerAddUpdateFragment updateFragment = new CustomerAddUpdateFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Utility.SELECTED_CUSTOMER, customer);
                updateFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.frame_layout, updateFragment);
                fragmentTransaction.commit();
                break;
            case R.id.action_delete:
                if (DAO.deleteCustomer(this, customer)) {
                    startActivity(new Intent(this, MainActivity.class));
                    Utility.showToast(this, "Successfully Deleted");
                } else {
                    Utility.showToast(this, "Error\n Try Again");
                }
                break;
            case R.id.action_share:
                CustomerViewFragment viewFragment = (CustomerViewFragment) getFragmentManager().findFragmentByTag(Utility.VIEW_FRAGMENT_TAG);
                CustomerAddUpdateFragment addUpdateFragment = (CustomerAddUpdateFragment) getFragmentManager().findFragmentByTag(Utility.ADD_UPDATE_FRAGMENT_TAG);
                CustomerModel customer = null;
                if (viewFragment != null && viewFragment.isVisible()) {
                    customer = viewFragment.getSelectedCustomer();
                }
                if (addUpdateFragment != null && addUpdateFragment.isVisible()) {
                    customer = addUpdateFragment.getSelectedCustomer();
                }
                if (customer != null) {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "Measurements : " + customer.getName() + "\nL : " + customer.getL()
                            + "\nC : " + customer.getC()
                            + "\nW : " + customer.getW()
                            + "\nH : " + customer.getH()
                            + "\nT : " + customer.getT()
                            + "\nS : " + customer.getS()
                            + "\nB : " + customer.getB()
                            + "\nM : " + customer.getM()
                            + "\nNF : " + customer.getNf()
                            + "\nNB : " + customer.getNb()
                            + "\nCHK : " + customer.getChk()
                            + "\nGHR : " + customer.getGhr()
                            + "\nSLVR : " + customer.getSlvr()
                            + "\nP : " + customer.getP()
                            + "\nTROUSER : " + customer.getTrouser()
                            + "\nTHG : " + customer.getThg()
                            + "\nPAJAMI : " + customer.getPajami()
                            + "\n1. : " + customer.getPajami1()
                            + "\n2. : " + customer.getPajami2()
                            + "\n3. : " + customer.getPajami3()
                            + "\nR : " + customer.getR()
                            + "\nBR : " + customer.getBr()
                            + "\nWR : " + customer.getWr()
                            + "\nRW : " + customer.getRw()
                            + "\nRH : " + customer.getRh()
                            + "\nExtra Comments : " + customer.getExtraComments();
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } else {
                    Utility.showToast(this, "Update in progress !!!");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}