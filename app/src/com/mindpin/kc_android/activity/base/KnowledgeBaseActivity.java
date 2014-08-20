package com.mindpin.kc_android.activity.base;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.destinyd.menudrawer.KCVerticalDrawerHandler;
import com.github.destinyd.menudrawer.common.DisplayModule;
import com.mindpin.kc_android.R;

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


        LinearLayout title_bar = (LinearLayout)findViewById(R.id.title_bar);

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
    }
}
