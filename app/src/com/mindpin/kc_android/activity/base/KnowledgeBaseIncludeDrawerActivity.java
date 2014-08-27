package com.mindpin.kc_android.activity.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.TopicListFragment;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.widget.SelectableLinearLayout;

/**
 * Created by fushang318 on 2014/8/26.
 */
public class KnowledgeBaseIncludeDrawerActivity extends KnowledgeBaseActivity{
    private KCVerticalDrawerHandler kcVerticalDrawerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kcVerticalDrawerHandler = new KCVerticalDrawerHandler(this);
        kcVerticalDrawerHandler.add_background_view(R.layout.drawer_menu);
        kcVerticalDrawerHandler.set_foreground_opening_offset(50);
        kcVerticalDrawerHandler.set_drop_shadow_size_px(10);
        kcVerticalDrawerHandler.set_drop_shadow_color(Color.parseColor("#999999"));
        kcVerticalDrawerHandler.set_drop_shadow_enable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        _init_title_bar();
    }

    private void _init_title_bar() {
        Button btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kcVerticalDrawerHandler.is_open()){
                    kcVerticalDrawerHandler.close();
                }else{
                    kcVerticalDrawerHandler.open();
                }
            }
        });


        // 打开测试 topic fragment 页面
        SelectableLinearLayout btn_menu_topic = (SelectableLinearLayout) findViewById(R.id.sll_all_classes);
        btn_menu_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new TopicListFragment());
                kcVerticalDrawerHandler.close();
            }
        });

        View view_menu = kcVerticalDrawerHandler.get_background_view();
        ((SelectableLinearLayout)view_menu.findViewById(R.id.sll_all_classes)).select();
        User user = User.current();
        if(user != null)
            ((TextView)view_menu.findViewById(R.id.tv_user_name)).setText(user.name);
    }
}
