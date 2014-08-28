package com.mindpin.kc_android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mindpin.android.authenticator.AuthCallback;
import com.mindpin.android.authenticator.IUser;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;

/**
 * Created by dd on 14-6-12.
 */
public class SignInActivity extends KnowledgeBaseActivity {
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
                // 关闭软键盘
                // TODO
                // 如果不关闭软键盘会触发下个页面的抽屉导航BUG，从而使页面显示不正常
                // 先用关闭软键盘的方式避免触发抽屉导航BUG
                InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_NOT_ALWAYS);
                }

                btn_signin.setEnabled(false);
                myAuthenticator.sign_in(
                        et_login.getText().toString(),
                        et_password.getText().toString(),
                        new AuthCallback() {
                            @Override
                            public void success(IUser user) {
                                SignInActivity.this.finish();
                                startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
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
