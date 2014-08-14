package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.github.kevinsawicki.http.HttpRequest;
import com.mindpin.android.authenticator.RequestCallback;
import com.mindpin.android.authenticator.RequestResult;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.utils.NetworkUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AuthenticatorsController auth = new AuthenticatorsController(this);
        User user = (User)auth.current_user();
        if(user != null){
            HttpRequest request = HttpRequest.get(auth.get_user_info_url());
            auth.request(request, new RequestCallback() {
                @Override
                public void is_200(RequestResult request) {
                    go_to_dashboard();
                }

                @Override
                public void not_200(RequestResult request) {
                    go_to_sign();
                }

                @Override
                public void error() {
                    request_error();
                }
            });
        }else{
            go_to_sign();
        }
    }

    private void request_error() {
        NetworkUtils.check_network_state(this);
    }

    private void go_to_dashboard(){
        this.finish();
        startActivity(new Intent(this,KnowledgeNetListActivity.class));
    }

    private void go_to_sign(){
        this.finish();
        startActivity(new Intent(this,SignInActivity.class));
    }






}