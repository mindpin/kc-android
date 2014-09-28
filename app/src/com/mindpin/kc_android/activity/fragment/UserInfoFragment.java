package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.SignInActivity;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;

/**
 * Created by Administrator on 2014/9/28.
 */
public class UserInfoFragment extends KnowledgeBaseFragment {
    private Activity activity;
    private View current_view;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.user_info_fragment, null);
        return current_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button logout_btn = (Button)current_view.findViewById(R.id.logout);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticatorsController auth = new AuthenticatorsController(activity);
                auth.sign_out((User)auth.current_user());
                activity.finish();
                startActivity(new Intent(activity,SignInActivity.class));
            }
        });
    }

}
