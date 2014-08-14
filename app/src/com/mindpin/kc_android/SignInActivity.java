package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.mindpin.android.authenticator.AuthCallback;
import com.mindpin.android.authenticator.IUser;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;

/**
 * Created by dd on 14-6-12.
 */
public class SignInActivity extends Activity {
    AuthenticatorsController myAuthenticator;
    User current_user;
    EditText et_login, et_password;
    Button btn_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sign_in);
        myAuthenticator = new AuthenticatorsController(this);
        current_user = User.current();
        et_login = (EditText) findViewById(R.id.et_login);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_signin.setEnabled(false);
                myAuthenticator.sign_in(
                        et_login.getText().toString(),
                        et_password.getText().toString(),
                        new AuthCallback() {
                            @Override
                            public void success(IUser user) {
                                SignInActivity.this.finish();
                                startActivity(new Intent(SignInActivity.this, KnowledgeNetListActivity.class));
                            }

                            @Override
                            public void failure() {
                                Toast.makeText(SignInActivity.this, "用户和密码不正确", Toast.LENGTH_LONG).show();
                                btn_signin.setEnabled(true);
                            }

                            @Override
                            public void error() {
                                Toast.makeText(SignInActivity.this, "连接服务器出错", Toast.LENGTH_LONG).show();
                                btn_signin.setEnabled(true);
                            }
                        });
            }
        });
    }
}
