package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.adapter.TopicTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.utils.BaseUtils;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.mindpin.kc_android.utils.UiFont;
import com.nostra13.universalimageloader.core.ImageLoader;

import it.sephiroth.android.library.widget.AbsHListView;
import it.sephiroth.android.library.widget.HListView;

/**
 * Created by fushang318 on 2014/8/22.
 */
public class TopicTutorialListActivity extends KnowledgeBaseActivity{
    private ITopic topic;

    private LoadingView loading_view;
    private HListView topic_tutorial_list_view;
    private ImageView topic_icon_img;
    private TextView topic_title_tv;
    private TextView topic_desc_tv;
    private Bitmap topic_background;
    private TextView topic_tutorial_list_bg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_tutorial_list);

        topic = (ITopic)getIntent().getSerializableExtra("topic");
        loading_view = (LoadingView) findViewById(R.id.loading_view);
        topic_tutorial_list_view = (HListView) findViewById(R.id.topic_tutorial_list);
        topic_tutorial_list_bg_tv = (TextView)findViewById(R.id.topic_tutorial_list_bg_tv);
        topic_icon_img = (ImageView)findViewById(R.id.topic_icon_img);
        Button back_btn = (Button)findViewById(R.id.back_btn);
        back_btn.setTypeface(UiFont.FONTAWESOME_FONT);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TopicTutorialListActivity.this.finish();
            }
        });

        topic_title_tv = (TextView)findViewById(R.id.topic_title_tv);
        topic_desc_tv = (TextView)findViewById(R.id.topic_desc_tv);

        topic_title_tv.setText(topic.get_title());
        topic_desc_tv.setText(topic.get_desc());

        get_datas();
    }

    public void get_datas() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                topic.get_tutorial_list();
                topic_background = ImageLoader.getInstance().loadImageSync(topic.get_icon_url());
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                topic_icon_img.setImageBitmap(topic_background);
                loading_view.hide();
            }
        }.execute();
    }

    private void build_view() {
        TopicTutorialListAdapter adapter = new TopicTutorialListAdapter(this);
        adapter.add_items(topic.get_tutorial_list());
        topic_tutorial_list_view.setAdapter(adapter);

        topic_tutorial_list_view.setOnItemClickListener(new it.sephiroth.android.library.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(it.sephiroth.android.library.widget.AdapterView<?> parent, View view, int position, long id) {

                ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);

                Intent intent = new Intent(TopicTutorialListActivity.this, TutorialActivity.class);
                intent.putExtra("tutorial", tutorial);
                startActivity(intent);
            }
        });

        _set_topic_tutorial_list_bg_tv_layout();
        _set_topic_tutorial_list_layout();
    }

    private void _set_topic_tutorial_list_layout(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels * 45 / 100;
        int height = width * 2 + BaseUtils.dp_to_px(10);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, height);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        topic_tutorial_list_view.setLayoutParams(lp);
    }

    private void _set_topic_tutorial_list_bg_tv_layout(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels * 45 / 100;
        int bg_height = width * 2 + BaseUtils.dp_to_px(10);
        RelativeLayout.LayoutParams bg_lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, bg_height);
        bg_lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        topic_tutorial_list_bg_tv.setLayoutParams(bg_lp);
    }
}
