package com.mindpin.kc_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.KnowledgeNetTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;
import roboguice.activity.RoboActivity;

import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetActivity extends RoboActivity implements View.OnClickListener {
    private IKnowledgeNet net;
    private List<ITutorial> tutorials;
    private List<IKnowledgePoint> points;
    private TextView tv_description, tv_title;
    private String knowledge_net_id;
    private LoadingView loading_view;
    private KnowledgeNetTutorialListAdapter adapter_tutorials;
    private ListView lv_tutorials;
    private ListView lv_points;
    private KnowledgeNetPointListAdapter adapter_points;
    private ToggleButton btn_tutorials, btn_points;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_description = (TextView) findViewById(R.id.tv_description);
        lv_tutorials = (ListView) findViewById(R.id.lv_list_tutorials);
        lv_points = (ListView) findViewById(R.id.lv_list_points);
        btn_tutorials = (ToggleButton) findViewById(R.id.btn_tutorials);
        btn_points = (ToggleButton) findViewById(R.id.btn_points);
        btn_tutorials.setOnClickListener(this);
        btn_points.setOnClickListener(this);
        loading_view = (LoadingView) findViewById(R.id.loading_view);

        this.knowledge_net_id = getIntent().getStringExtra("knowledge_net_id");
        get_datas();

    }

    private void get_datas() {
        new KCAsyncTask<Void>(this) {

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                net = DataProvider.get_knowledge_net(knowledge_net_id);
                tutorials = net.get_tutorial_list();
                points = net.get_knowledge_point_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }

    private void build_view() {
        tv_title.setText(net.get_name());
        tv_description.setText(net.get_desc());

        bind_tutorials();
        bind_points();
    }

    private void bind_tutorials() {
        adapter_tutorials = new KnowledgeNetTutorialListAdapter(this);
        adapter_tutorials.add_items(tutorials);
        lv_tutorials.setAdapter(adapter_tutorials);
        lv_tutorials.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);
                String tutorial_id = tutorial.get_id();

                Intent intent = new Intent(KnowledgeNetActivity.this, TutorialActivity.class);
                intent.putExtra("tutorial_id", tutorial_id);
                startActivity(intent);
            }
        });
    }

    private void bind_points() {
        adapter_points = new KnowledgeNetPointListAdapter(this);
        adapter_points.add_items(points);
        lv_points.setAdapter(adapter_points);
        lv_points.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                IKnowledgePoint knowledgePoint = (IKnowledgePoint) parent.getItemAtPosition(position);
                String knowledge_point_id = knowledgePoint.get_id();

                Intent intent = new Intent(KnowledgeNetActivity.this, KnowledgePointActivity.class);
                intent.putExtra("knowledge_point_id", knowledge_point_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tutorials) {
            lv_tutorials.setVisibility(View.VISIBLE);
            lv_points.setVisibility(View.GONE);
            btn_tutorials.setChecked(true);
            btn_points.setChecked(false);
        } else {
            lv_tutorials.setVisibility(View.GONE);
            lv_points.setVisibility(View.VISIBLE);
            btn_tutorials.setChecked(false);
            btn_points.setChecked(true);
        }
    }
}