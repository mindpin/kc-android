package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseIncludeDrawerActivity;
import com.mindpin.kc_android.activity.fragment.TopicListFragment;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;


public class DashboardActivity extends KnowledgeBaseIncludeDrawerActivity {
    private FragmentManager fragment_manager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);

        show_topic_list_fragment();
    }

    private void show_topic_list_fragment(){
        fragment_manager = getSupportFragmentManager();

        FragmentTransaction transaction = fragment_manager.beginTransaction();
        TopicListFragment topic_list_fragment = new TopicListFragment();
        transaction.add(R.id.frame_layout_content, topic_list_fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"登出");
        menu.add(0,1,0,"退出");
        return true;
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