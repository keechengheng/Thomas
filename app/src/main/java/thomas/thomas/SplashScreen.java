package thomas.thomas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by keechengheng on 29/6/15.
 */
public class SplashScreen extends AppCompatActivity{

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Intent mainIntent = new Intent(SplashScreen.this, StartupScreen.class);
        SplashScreen.this.startActivity(mainIntent);
/*
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
                String Status = sharedPreferences.getString("LoginStatus","Default");

                if(Status.equals("Default")){
                    Intent mainIntent = new Intent(SplashScreen.this, StartupScreen.class);
                    SplashScreen.this.startActivity(mainIntent);
                }
                if(Status.equals("LO")){
                    Intent mainIntent = new Intent(SplashScreen.this, StartupScreen.class);
                    SplashScreen.this.startActivity(mainIntent);
                }
                if(Status.equals("LI")){
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    SplashScreen.this.startActivity(mainIntent);
                }



            }
        }, SPLASH_DISPLAY_LENGTH);
        */
    }
}
