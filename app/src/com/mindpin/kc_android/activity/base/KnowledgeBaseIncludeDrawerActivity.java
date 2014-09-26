package com.mindpin.kc_android.activity.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.fragment.MyStepListFragment;
import com.mindpin.kc_android.activity.fragment.MyTopicListFragment;
import com.mindpin.kc_android.activity.fragment.TopicListFragment;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.widget.SelectableLinearLayout;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by fushang318 on 2014/8/26.
 */
public class KnowledgeBaseIncludeDrawerActivity extends KnowledgeBaseActivity{
    private DrawerLayout drawer_layout;
    private LinearLayout left_drawer;

    // 所有课程
    SelectableLinearLayout btn_menu_topic;

    // 我的课程
    SelectableLinearLayout btn_menu_mytopic;

    // 我的难点
    SelectableLinearLayout btn_menu_mystep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        _init_drawer_layout();
    }

    private void _init_drawer_layout() {
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        left_drawer = (LinearLayout)findViewById(R.id.left_drawer);

        Button btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawer_layout.isDrawerOpen(left_drawer)){
                    drawer_layout.closeDrawer(left_drawer);
                }else{
                    drawer_layout.openDrawer(left_drawer);
                }
            }
        });

        // 点击所有课程
        btn_menu_topic = (SelectableLinearLayout) findViewById(R.id.sll_all_classes);

        // 默认选中所有课程
        btn_menu_topic.select();
        btn_menu_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new TopicListFragment());

                highlight_current_menu(left_drawer, btn_menu_topic);
            }
        });

        // 点击我的课程
        btn_menu_mytopic = (SelectableLinearLayout) findViewById(R.id.sll_my_classes);
        btn_menu_mytopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new MyTopicListFragment());

                highlight_current_menu(left_drawer, btn_menu_mytopic);
            }
        });


        // 点击我的难点
        btn_menu_mystep = (SelectableLinearLayout) findViewById(R.id.sll_my_difficulties);
        btn_menu_mystep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(new MyStepListFragment());

                highlight_current_menu(left_drawer, btn_menu_mystep);
            }
        });


        User user = User.current();
        if(user != null){
            ((TextView)findViewById(R.id.tv_user_name)).setText(user.name);
            ImageLoader.getInstance().displayImage(
                user.get_avatar_url(),
                ((CircleImageView) findViewById(R.id.iv_user_avatar))
            );
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if(drawer_layout.isDrawerOpen(left_drawer)){
                drawer_layout.closeDrawer(left_drawer);
                return true;
            }else{
                new AlertDialog.Builder(this)
                    .setMessage("要退出吗？")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    }).show();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    // 选中当前菜单，左侧蓝条
    protected void highlight_current_menu(LinearLayout left_drawer,
                                   SelectableLinearLayout selected_drawer) {

        int childcount = left_drawer.getChildCount();
        for (int i=0; i < childcount; i++){
            View v = left_drawer.getChildAt(i);
            if (v.getClass().equals(SelectableLinearLayout.class)) {
                Log.i("View id: ", Integer.toString(v.getId()));
                SelectableLinearLayout l =
                        (SelectableLinearLayout)findViewById(v.getId());
                if (l != null) {
                    Log.i("取消选中状态 : ", Integer.toString(v.getId()));
                    l.unselect();
                }
            }
        }
        selected_drawer.select();

        drawer_layout.closeDrawer(left_drawer);
    }
}
