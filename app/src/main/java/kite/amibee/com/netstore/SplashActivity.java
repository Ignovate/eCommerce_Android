package kite.amibee.com.netstore;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import kite.amibee.com.netstore.activity.home.HomeActivity;
import kite.amibee.com.netstore.activity.register.SignInActivity;
import kite.amibee.com.netstore.util.PreferencesHelper;
import kite.amibee.com.netstore.util.api.Api;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    PreferencesHelper preferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    public void init(){
        preferencesHelper = new PreferencesHelper(this);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (preferencesHelper.getUserRegister()){
                    Intent mainIntent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                }else {
                    Intent mainIntent = new Intent(SplashActivity.this,SignInActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
                    finish();
                }


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
