package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;

/**
 * Created by dd on 14-8-1.
 */
public class DashboardActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"登出");
        menu.add(0,1,0,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                sign_out();
                break;
            case 1:
                exit_app();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sign_out() {
        AuthenticatorsController auth = new AuthenticatorsController(this);
        auth.sign_out((User)auth.current_user());
        this.finish();
        startActivity(new Intent(this,SignInActivity.class));
    }

    private void exit_app() {
        this.finish();
    }
}