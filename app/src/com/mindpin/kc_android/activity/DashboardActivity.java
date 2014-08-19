package com.mindpin.kc_android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.activity.fragment.KnowledgeNetListFragment;


public class DashboardActivity extends KnowledgeBaseActivity {
    private FragmentManager fragment_manager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_content);

        show_knowledge_net_list_fragment();
    }

    private void show_knowledge_net_list_fragment(){
        fragment_manager = getSupportFragmentManager();

        FragmentTransaction transaction = fragment_manager.beginTransaction();
        KnowledgeNetListFragment knowledge_net_list_fragment = new KnowledgeNetListFragment();
        transaction.add(R.id.frame_layout_content, knowledge_net_list_fragment).commit();
    }

    // TODO 退出登录和登出应用，暂时没有地方放置
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0,0,0,"登出");
//        menu.add(0,1,0,"退出");
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case 0:
//                sign_out();
//                break;
//            case 1:
//                exit_app();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//
//
//    private void sign_out() {
//        AuthenticatorsController auth = new AuthenticatorsController(this);
//        auth.sign_out((User)auth.current_user());
//        this.finish();
//        startActivity(new Intent(this,SignInActivity.class));
//    }
//
//    private void exit_app() {
//        this.finish();
//    }
}