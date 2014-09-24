package com.mindpin.kc_android.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mindpin.android.kcroundprogressbar.KCRoundProgressBar;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.adapter.TutorialTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.mindpin.kc_android.utils.ListViewUtils;

import java.util.List;


public class KnowledgePointActivity extends KnowledgeBaseActivity
        implements AdapterView.OnItemClickListener {

    private IKnowledgePoint point;
    List<ITutorial> tutorials;
    private TutorialTutorialListAdapter adapter_tutorials;

    // 教程列表
    private ListView listview_tutorials;
    private TextView view_no_tutorials;

    // 知识简介
    private TextView view_knowledge_point_desc;
    private String knowledge_point_desc;

    // 知识标题
    private TextView view_knowledge_point_title;
    private KCRoundProgressBar view_knowledge_point_progress;

    private LinearLayout view_circle_box;
    private LinearLayout view_title_box;

    private String knowledge_point_id;
    private LoadingView loading_view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_point_activity);

        load_view();
        get_datas();
    }


    private void get_datas() {
        // knowledge_point_id = "53fdc692286e6f4d70100000";

        point = (IKnowledgePoint)getIntent().getSerializableExtra("point");
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                // loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                // point = DataProvider.get_knowledge_point(knowledge_point_id);
                point = (IKnowledgePoint)getIntent().getSerializableExtra("point");

                point.get_knowledge_net();
                knowledge_point_desc = point.get_desc();
                tutorials = point.get_tutorial_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                show_knowledge_point_desc();
                show_tutorials();
                show_knowledge_point_circle();
                show_knowledge_point_title();
                // build_view();
                // loading_view.hide();
            }
        }.execute();
    }


    private void show_tutorials() {
        if(tutorials.size() >0 ) {
            adapter_tutorials = new TutorialTutorialListAdapter(this);
            adapter_tutorials.add_items(tutorials);
            listview_tutorials.setAdapter(adapter_tutorials);
            listview_tutorials.setVisibility(View.VISIBLE);
            view_no_tutorials.setVisibility(View.GONE);
            ListViewUtils.setListViewHeightBasedOnChildren(listview_tutorials);

            listview_tutorials.setOnItemClickListener(this);
        }
        else{
            view_no_tutorials.setVisibility(View.VISIBLE);
            listview_tutorials.setVisibility(View.GONE);
        }
    }


    private void show_knowledge_point_desc() {
        if (knowledge_point_desc != null) {
            view_knowledge_point_desc.setText(knowledge_point_desc);
        }
    }

    private void show_knowledge_point_circle() {
        view_knowledge_point_progress.set_current(100);
        view_knowledge_point_progress.set_fg_color(Color.parseColor(point.get_color()));
        view_knowledge_point_progress.set_thickness(9/35);
    }

    private void show_knowledge_point_title() {
        view_knowledge_point_title.setText(point.get_name());
    }

    private void load_view() {
        view_circle_box = (LinearLayout) findViewById(R.id.circle_box);
        view_title_box = (LinearLayout) findViewById(R.id.title_box);

        // 高度是屏幕的 1/3
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        view_circle_box.setMinimumHeight(width / 3);

        // 保证包围圆形的外层容器宽高一样
        Log.i("圆形外层容器宽高 ", Integer.toString(width / 3));
        view_circle_box.setMinimumWidth(width / 3);

        view_no_tutorials = (TextView) findViewById(R.id.tv_previous_none);
        listview_tutorials = (ListView) findViewById(R.id.lv_previous);

        view_knowledge_point_desc = (TextView) findViewById(R.id.knowledge_point_desc);
        view_knowledge_point_title = (TextView) findViewById(R.id.knowledge_point_title);

        view_knowledge_point_progress =
                (KCRoundProgressBar) findViewById(R.id.knowledge_point_progress);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);

        tidy_by_class(TopicTutorialListActivity.class);

        Intent intent = new Intent(KnowledgePointActivity.this, TutorialActivity.class);
        intent.putExtra("tutorial", tutorial);
        startActivity(intent);
    }
}