package com.mindpin.kc_android.activity.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.mindpin.kc_android.R;
import roboguice.activity.RoboFragmentActivity;

/**
 * Created by fushang318 on 2014/8/14.
 */
public class KnowledgeBaseActivity extends RoboFragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivitiesStackSingleton.tidy_and_push_activity(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivitiesStackSingleton.remove_activity(this);
    }

    protected  void change_fragment(Fragment fragment){
        Bundle args = new Bundle();
        change_fragment(fragment,args);
    }

    protected void change_fragment(Fragment fragment, Bundle args){
        FragmentManager fragment_manager = getSupportFragmentManager();
        FragmentTransaction transaction = fragment_manager.beginTransaction();
        fragment.setArguments(args);
        transaction.replace(R.id.frame_layout_content, fragment, "topic");
        transaction.addToBackStack("topic").commit();
    }
}
