package com.mindpin.kc_android.activity.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.SignInActivity;
import com.mindpin.kc_android.activity.base.KnowledgeBaseIncludeDrawerActivity;
import com.mindpin.kc_android.activity.fragment.base.KnowledgeBaseFragment;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.utils.UiFont;
import com.mindpin.kc_android.widget.BorderRadiusLinearLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

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
        ((KnowledgeBaseIncludeDrawerActivity)activity).set_drawer_title(R.string.user_info);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        current_view = inflater.inflate(R.layout.user_info_fragment, null);
        return current_view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        User user = User.current();
        if(user != null){
            ((TextView)current_view.findViewById(R.id.tv_user_name)).setText(user.name);
            ImageLoader.getInstance().displayImage(
                    user.get_avatar_url(),
                    ((CircleImageView) current_view.findViewById(R.id.iv_user_avatar))
            );
        }

        TextView logout_text_tv = (TextView)current_view.findViewById(R.id.logout_text_tv);
        logout_text_tv.setTypeface(UiFont.FONTAWESOME_FONT);

        BorderRadiusLinearLayout logout_btn = (BorderRadiusLinearLayout)current_view.findViewById(R.id.logout);
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
