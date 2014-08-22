package com.mindpin.kc_android.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;
import com.github.destinyd.menudrawer.common.DisplayModule;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.TopicListFragment;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.widget.SelectableLinearLayout;
import roboguice.activity.RoboFragmentActivity;

/**
 * Created by fushang318 on 2014/8/14.
 */
public class KnowledgeBaseActivity extends RoboFragmentActivity{
    private KCVerticalDrawerHandler kcVerticalDrawerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(findViewById(R.id.title_bar) != null){
            _init_title_bar();
        }
    }

    private void _init_title_bar() {
        kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
        kcVerticalDrawerHandler.add_background_view(R.layout.drawer_menu);
        kcVerticalDrawerHandler.set_foreground_opening_offset(DisplayModule.px_to_dp(this, 50));
        kcVerticalDrawerHandler.set_drop_shadow_size_px(10);
        kcVerticalDrawerHandler.set_drop_shadow_color(Color.parseColor("#999999"));
        kcVerticalDrawerHandler.set_drop_shadow_enable();


        RelativeLayout title_bar = (RelativeLayout)findViewById(R.id.title_bar);

        Button btn_menu = (Button) findViewById(R.id.btn_menu);
        Log.v("kc", "menu init click");
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("kc", "menu click");
                if(view.getTag() == null ){
                    view.setTag("close");
                }
                Log.v("kc", view.getTag() + "");
                String tag = (String) view.getTag();
                if(tag.equals("close")){
                    Log.v("kc", ">>> close");
                    kcVerticalDrawerHandler.open();
                    view.setTag("open");
                }else if(tag.equals("open")){
                    Log.v("kc", ">>> open");
                    kcVerticalDrawerHandler.close();
                    view.setTag("close");
                }
            }
        });


        // 打开测试 topic fragment 页面
        SelectableLinearLayout btn_menu_topic = (SelectableLinearLayout) findViewById(R.id.sll_all_classes);
        btn_menu_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("kc", "btn_menu_topic click");
                change_fragment(new TopicListFragment());

                kcVerticalDrawerHandler.close();
                view.setTag("close");
            }
        });

        View view_menu = kcVerticalDrawerHandler.get_background_view();
        ((SelectableLinearLayout)view_menu.findViewById(R.id.sll_all_classes)).select();
        User user = User.current();
        if(user != null)
            ((TextView)view_menu.findViewById(R.id.tv_user_name)).setText(user.name);
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
