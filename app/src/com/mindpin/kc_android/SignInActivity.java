package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dd on 14-6-12.
 */
public class SignInActivity extends Activity {
//    MyAuthenticator myAuthenticator;
//    User current_user;
    EditText et_login, et_password;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);
//        myAuthenticator = new MyAuthenticator();
//        current_user = User.current();
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                myAuthenticator.sign_in(
//                        et_login.getText().toString(),
//                        et_password.getText().toString(),
//                        new AuthSuccessCallback() {
//                            @Override
//                            public void callback(IUser user) {
//                                if (user == null) {
//                                    Toast.makeText(AuthenticatorSignIn.this, "登录失败!", Toast.LENGTH_LONG).show();
//                                } else {
//                                    to_signout();
//                                }
//                            }
//                        });
            }
        });
    }
}
