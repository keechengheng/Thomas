package thomas.thomas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

import java.net.MalformedURLException;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by keechengheng on 29/6/15.
 */
public class StartupScreen extends AppCompatActivity {

    //Register Tab Variables

    private EditText rP = null;
    private EditText rE = null;
    private EditText rCP = null;
    private Button register;

    //Login Tab Variables
    private Button login;
    private EditText lP = null;
    private EditText lE = null;

    //connect to mobile db
    private MobileServiceClient mClient;

    public static final String ENDPOINT =
            "http://apilumi.azurewebsites.net/api";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_page);



        //Register Tab Variable Extraction)
        rCP = (EditText) findViewById(R.id.rConfirmPassword);
        rE = (EditText) findViewById(R.id.rEmail);
        rP = (EditText) findViewById(R.id.rPassword);
        register = (Button) findViewById(R.id.button1);

        //Login Tab Variable Extraction
        lP = (EditText) findViewById(R.id.lPassword);
        lE = (EditText) findViewById(R.id.lEmail);
        login = (Button) findViewById(R.id.btnLogin);

        //TabHost Variable Extraction
        final TabHost tabHost = (TabHost) findViewById(R.id.tabHost);

        /*********************************TabHost Setup*************************************/
        tabHost.setup();

        //Creation of Login TabHost
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("Login");
        tabSpec.setContent(R.id.login);
        tabSpec.setIndicator("Login");
        tabHost.addTab(tabSpec);

        //Creation of Register TaHost
        tabSpec = tabHost.newTabSpec("Register");
        tabSpec.setContent(R.id.register);
        tabSpec.setIndicator("Register");
        tabHost.addTab(tabSpec);

        //Listener to detect Tab Change
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String arg0) {
                setTabColor(tabHost);
            }
        });
        setTabColor(tabHost);
    }



    /**
     * **************************************METHOD TO CHANGE TAB COLOR***********************************************************
     */
    public void setTabColor(TabHost tabhost) {

        for (int i = 0; i < tabhost.getTabWidget().getChildCount(); i++)
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FF797979")); //unselected

        if (tabhost.getCurrentTab() == 0)
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFF386B")); //1st tab selected
        else
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFF386B")); //2nd tab selected
    }
    /**
     * **********************************WHEN REGISTER BUTTON IS PRESSED***********************************************************
     */
    public void register(View view) {

        if (rCP.getText().toString().equals(rP.getText().toString())) {

            // Create a new item
            final myUser user = new myUser();

           user.mEmail = rE.toString();

    	// Insert the new item
            mClient.getTable(myUser.class).insert(user, new TableOperationCallback<myUser>() {
                public void onCompleted(myUser entity, Exception exception, ServiceFilterResponse response) {
                    if (exception == null) {
                        Toast.makeText(getApplicationContext(), "Successfully Created", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to Create", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            rCP.setError("Make sure that passwords match");
        }
    }

    /**
     * **********************************WHEN LOGIN BUTTON IS PRESSED**************************************************************
     */
    public void login(View view) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        LumiApiService service = restAdapter.create(LumiApiService.class);

        service.getLoginForID(1, new Callback<Login>(){

                    @Override
                public  void success (Login Login, Response response){
                        Toast.makeText(getApplicationContext(), "Successfully Created", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public  void failure (RetrofitError error){
                        Toast.makeText(getApplicationContext(), "Failed to Create", Toast.LENGTH_SHORT).show();
                    }

        });



        Intent mainIntent = new Intent(StartupScreen.this, MainActivity.class);
        StartupScreen.this.startActivity(mainIntent);
    }



    }
