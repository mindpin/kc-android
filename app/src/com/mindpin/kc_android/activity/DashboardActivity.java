package com.mindpin.kc_android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseIncludeDrawerActivity;
import com.mindpin.kc_android.activity.fragment.TopicListFragment;

public class DashboardActivity extends KnowledgeBaseIncludeDrawerActivity {
    private FragmentManager fragment_manager;
    private boolean is_init = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!is_init){
            show_topic_list_fragment();
            is_init = true;
        }
    }

    private void show_topic_list_fragment(){
        fragment_manager = getSupportFragmentManager();

        FragmentTransaction transaction = fragment_manager.beginTransaction();
        TopicListFragment topic_list_fragment = new TopicListFragment();
        transaction.add(R.id.frame_layout_content, topic_list_fragment).commit();
    }

}