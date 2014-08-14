package com.mindpin.kc_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.github.destinyd.FlipBriefLayout;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.TutorialStepListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import roboguice.activity.RoboActivity;

/**
 * Created by dd on 14-8-11.
 */
public class TutorialActivity extends RoboActivity {

    private static final String TAG = "TutorialActivity";
    // brief
//    @InjectView(R.id.lv_list_brief)
    protected ListView lv_list_brief;
//    @InjectView(R.id.tv_description_brief)
    protected TextView tv_description_brief;
//    @InjectView(R.id.iv_cover)
    protected ImageView iv_cover;

    //detail
//    @InjectView(R.id.lv_list_detail)
    protected ListView lv_list_detail;
//    @InjectView(R.id.tv_title_detail)
    protected TextView tv_title_detail;
//    @InjectView(R.id.tv_description_detail)
    protected TextView tv_description_detail;

    View brief;
    View detail;
    ITutorial tutorial;
    FlipBriefLayout kcFlip;
    KnowledgeNetPointListAdapter adapter_brief;
    TutorialStepListAdapter adapter_detail;
    List<IKnowledgePoint> point_list;
    List<IStep> step_list;
    LoadingView loading_view;
    String tutorial_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
        tutorial_id = getIntent().getStringExtra("tutorial_id");
        kcFlip = (FlipBriefLayout) findViewById(R.id.kcflip);
        loading_view = (LoadingView) findViewById(R.id.loading_view);


        get_and_add_layouts_to_flip();
        find_views();
        get_datas();
    }

    private void datas_to_brief() {
        tv_description_brief.setText(tutorial.get_desc());
        ImageLoader.getInstance().displayImage(tutorial.get_icon_url(), iv_cover);
        adapter_brief = new KnowledgeNetPointListAdapter(this);
        adapter_brief.add_items(point_list);
        lv_list_brief.setAdapter(adapter_brief);
        lv_list_brief.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                IKnowledgePoint knowledgePoint = (IKnowledgePoint) parent.getItemAtPosition(position);
                String knowledge_point_id = knowledgePoint.get_id();

                Intent intent = new Intent(TutorialActivity.this, KnowledgePointActivity.class);
                intent.putExtra("knowledge_point_id", knowledge_point_id);
                startActivity(intent);
            }
        });
    }

    private void datas_to_detail() {
        tv_title_detail.setText(tutorial.get_title());
        tv_description_detail.setText(tutorial.get_desc());
        adapter_detail = new TutorialStepListAdapter(this);
        adapter_detail.add_items(step_list);
        lv_list_detail.setAdapter(adapter_detail);
    }


    private void get_datas() {
        new KCAsyncTask<Void>(this){

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                tutorial = DataProvider.get_tutorial(tutorial_id);
                point_list = tutorial.get_related_knowledge_point_list();
                step_list = tutorial.get_step_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                System.out.println(tutorial.get_title());
                datas_to_brief();
                datas_to_detail();
                loading_view.hide();
            }
        }.execute();
    }

    private void get_and_add_layouts_to_flip() {
        brief = LayoutInflater.from(this).inflate(R.layout.tutorial_brief, null);
        detail = LayoutInflater.from(this).inflate(R.layout.tutorial_detail, null);
        kcFlip.set_brief_view(brief);
        kcFlip.set_detail_view(detail);
    }

    private void find_views() {
        find_brief_views();
        find_detail_views();
    }

    private void find_detail_views() {
        lv_list_detail = (ListView)detail.findViewById(R.id.lv_list_detail);
        tv_title_detail = (TextView)detail.findViewById(R.id.tv_title_detail);
        tv_description_detail = (TextView)detail.findViewById(R.id.tv_description_detail);
    }

    private void find_brief_views() {
        lv_list_brief = (ListView)brief.findViewById(R.id.lv_list_brief);
        tv_description_brief = (TextView)brief.findViewById(R.id.tv_description_brief);
        iv_cover = (ImageView)brief.findViewById(R.id.iv_cover);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kcFlip.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        kcFlip.onPause();
    }
}