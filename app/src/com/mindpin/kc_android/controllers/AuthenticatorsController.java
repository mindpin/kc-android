package com.mindpin.kc_android.controllers;

import android.content.Context;

import com.google.gson.Gson;
import com.mindpin.android.authenticator.Authenticator;
import com.mindpin.android.authenticator.IUser;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.models.User;

/**
 * Created by dd on 14-6-10.
 */
public class AuthenticatorsController extends Authenticator<User> {
    private static final String TAG = "MyAuthenticator";
    Context context;

    public AuthenticatorsController(Context context) {
        this.context = context;
    }

    public String get_sign_in_url() {
        return getHttpSite() + "/account/sign_in.json";
//        return "http://kc-alpha.4ye.me/account/sign_in.json";
    }

    public String get_login_param() {
        return "user[login]";
    }

    public String get_password_param() {
        return "user[password]";
    }

    public User on_auth_success_build_user(String response) {
        //  根据 response 构建出 user 对象
        return new Gson().fromJson(response, User.class);
    }

    public String get_user_info_url() {
        return getHttpSite() + "/api/nets";
//        return "http://kc-alpha.4ye.me/api/nets";
    }

    @Override
    public IUser current_user() {
        return User.current();
    }

    private String getHttpSite(){
        return context.getResources().getString(R.string.http_site);
    }
}
