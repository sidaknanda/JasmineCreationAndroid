package com.android.jcandroid;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.jcandroid.Database.DAO;
import com.android.jcandroid.Model.CustomerModel;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GooglePlayServicesAvailabilityIOException;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ArrayMap;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.script.model.ExecutionRequest;
import com.google.api.services.script.model.Operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CloudSyncInActivity extends AppCompatActivity {
    GoogleAccountCredential mCredential;
    private TextView textView;
    ProgressDialog mProgress;

    static final int REQUEST_ACCOUNT_PICKER = 1000;
    static final int REQUEST_AUTHORIZATION = 1001;
    static final int REQUEST_GOOGLE_PLAY_SERVICES = 1002;
    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = {"https://www.googleapis.com/auth/spreadsheets"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mProgress = Utility.getProgressDialog(this, "Getting customers data from Cloud");

        setContentView(R.layout.activity_sync);
        textView = (TextView) findViewById(R.id.textViewMain);

        SharedPreferences settings = getSharedPreferences(Utility.PREF_APP, Context.MODE_PRIVATE);
        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff())
                .setSelectedAccountName(settings.getString(PREF_ACCOUNT_NAME, null));
    }

    private static HttpRequestInitializer setHttpTimeout(
            final HttpRequestInitializer requestInitializer) {
        return new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest)
                    throws IOException {
                requestInitializer.initialize(httpRequest);
                httpRequest.setReadTimeout(380000);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isGooglePlayServicesAvailable()) {
            refreshResults();
        } else {
            textView.setText("Google Play Services required: " +
                    "after installing, close and relaunch this app.");
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GOOGLE_PLAY_SERVICES:
                if (resultCode != RESULT_OK) {
                    isGooglePlayServicesAvailable();
                }
                break;
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null &&
                        data.getExtras() != null) {
                    String accountName =
                            data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        mCredential.setSelectedAccountName(accountName);
                        SharedPreferences settings =
                                getSharedPreferences(Utility.PREF_APP, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString(PREF_ACCOUNT_NAME, accountName);
                        editor.apply();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    textView.setText("Account unspecified.");
                }
                break;
            case REQUEST_AUTHORIZATION:
                if (resultCode != RESULT_OK) {
                    chooseAccount();
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refreshResults() {
        if (mCredential.getSelectedAccountName() == null) {
            chooseAccount();
        } else {
            if (isDeviceOnline()) {
                new MakeRequestTask(mCredential).execute();
            } else {
                textView.setText("No network connection available.");
            }
        }
    }

    private void chooseAccount() {
        startActivityForResult(
                mCredential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }

    private boolean isDeviceOnline() {
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServicesAvailable() {
        final int connectionStatusCode =
                GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (GooglePlayServicesUtil.isUserRecoverableError(connectionStatusCode)) {
            showGooglePlayServicesAvailabilityErrorDialog(connectionStatusCode);
            return false;
        } else if (connectionStatusCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode) {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                connectionStatusCode,
                CloudSyncInActivity.this,
                REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

    private class MakeRequestTask extends AsyncTask<Void, Void, ArrayList<CustomerModel>> {
        private com.google.api.services.script.Script mService = null;
        private Exception mLastError = null;

        public MakeRequestTask(GoogleAccountCredential credential) {
            HttpTransport transport = AndroidHttp.newCompatibleTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            mService = new com.google.api.services.script.Script.Builder(
                    transport, jsonFactory, setHttpTimeout(credential))
                    .setApplicationName("Google Apps Script Execution API Android Quickstart")
                    .build();
        }

        @Override
        protected ArrayList<CustomerModel> doInBackground(Void... params) {
            try {
                return getDataFromApi();
            } catch (Exception e) {
                mLastError = e;
                cancel(true);
                return null;
            }
        }

        private ArrayList<CustomerModel> getDataFromApi()
                throws IOException, GoogleAuthException {

            String scriptId = "M1G732RfxZJHgICoCrhhokoqedaDLp5WE";

            ExecutionRequest request = new ExecutionRequest()
                    .setFunction("getData")
                    .setDevMode(true);

            Operation op =
                    mService.scripts().run(scriptId, request).execute();

            ArrayList<ArrayMap<String, String>> tempList = null;
            if (op.getError() != null) {
                throw new IOException(getScriptError(op));
            }
            if (op.getResponse() != null &&
                    op.getResponse().get("result") != null) {
                tempList = (ArrayList<ArrayMap<String, String>>) op.getResponse().get("result");
            }

            ArrayList<CustomerModel> customersList = new ArrayList<>();
            for (int i = 1; i < tempList.size(); i++) {
                CustomerModel model = new CustomerModel();
                model.setName(tempList.get(i).get("Name"));
                model.setPhone(tempList.get(i).get("Phone"));
                model.setAddress(tempList.get(i).get("Address"));
                model.setL(tempList.get(i).get("L"));
                model.setC(tempList.get(i).get("C"));
                model.setW(tempList.get(i).get("W"));
                model.setH(tempList.get(i).get("H"));
                model.setT(tempList.get(i).get("T"));
                model.setS(tempList.get(i).get("S"));
                model.setB(tempList.get(i).get("B"));
                model.setM(tempList.get(i).get("M"));
                model.setNf(tempList.get(i).get("NF"));
                model.setNb(tempList.get(i).get("NB"));
                model.setChk(tempList.get(i).get("CHK"));
                model.setGhr(tempList.get(i).get("GHR"));
                model.setSlvr(tempList.get(i).get("SLVR"));
                model.setP(tempList.get(i).get("P"));
                model.setTrouser(tempList.get(i).get("TROUSER"));
                model.setThg(tempList.get(i).get("THG"));
                model.setPajami(tempList.get(i).get("PAJAMI"));
                model.setPajami1(tempList.get(i).get("1"));
                model.setPajami2(tempList.get(i).get("2"));
                model.setPajami3(tempList.get(i).get("3"));
                model.setR(tempList.get(i).get("R"));
                model.setBr(tempList.get(i).get("BR"));
                model.setWr(tempList.get(i).get("WR"));
                model.setRw(tempList.get(i).get("RW"));
                model.setRh(tempList.get(i).get("RH"));
                model.setExtraComments(tempList.get(i).get("ExtraComments"));
                customersList.add(model);
            }

            return customersList;
        }

        private String getScriptError(Operation op) {
            if (op.getError() == null) {
                return null;
            }

            Map<String, Object> detail = op.getError().getDetails().get(0);
            List<Map<String, Object>> stacktrace =
                    (List<Map<String, Object>>) detail.get("scriptStackTraceElements");

            StringBuilder sb =
                    new StringBuilder("\nScript error message: ");
            sb.append(detail.get("errorMessage"));

            if (stacktrace != null) {
                sb.append("\nScript error stacktrace:");
                for (Map<String, Object> elem : stacktrace) {
                    sb.append("\n  ");
                    sb.append(elem.get("function"));
                    sb.append(":");
                    sb.append(elem.get("lineNumber"));
                }
            }
            sb.append("\n");
            return sb.toString();
        }


        @Override
        protected void onPreExecute() {
            textView.setText("");
            mProgress.show();
        }

        @Override
        protected void onPostExecute(ArrayList<CustomerModel> output) {
            mProgress.hide();
            if (output == null || output.size() == 0) {
                textView.setText("No results returned.");
                DAO.createCustomerTableFromList(output, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                DAO.createCustomerTableFromList(output, getApplicationContext());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }

        @Override
        protected void onCancelled() {
            mProgress.hide();
            if (mLastError != null) {
                if (mLastError instanceof GooglePlayServicesAvailabilityIOException) {
                    showGooglePlayServicesAvailabilityErrorDialog(
                            ((GooglePlayServicesAvailabilityIOException) mLastError)
                                    .getConnectionStatusCode());
                } else if (mLastError instanceof UserRecoverableAuthIOException) {
                    startActivityForResult(
                            ((UserRecoverableAuthIOException) mLastError).getIntent(),
                            CloudSyncInActivity.REQUEST_AUTHORIZATION);
                } else {
                    Utility.showToast(getApplicationContext(), "The following error occurred:\n"
                            + mLastError.getMessage());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            } else {
                textView.setText("Request cancelled.");
            }
        }
    }
}