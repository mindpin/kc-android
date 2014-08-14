package com.mindpin.kc_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.io.Serializable;

import roboguice.activity.RoboTabActivity;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetActivity extends RoboTabActivity {
    private IKnowledgeNet net;
    TabHost tabHost;
    TextView tv_description, tv_title;
    private String knowledge_net_id;
    private LoadingView loading_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_description = (TextView) findViewById(R.id.tv_description);
        loading_view = (LoadingView) findViewById(R.id.loading_view);

        this.knowledge_net_id = getIntent().getStringExtra("knowledge_net_id");
        get_datas();

    }

    private void setTabs() {
        addTab("教程",
                KnowledgeNetTutorialsActivity.class);
        addTab("知识点",
                KnowledgeNetPointsActivity.class);
    }

    private void addTab(String labelId,
                        Class<?> c, String extra, Serializable net) {
        Intent intent = new Intent(this, c);
        intent.putExtra(extra, net);
        TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);

        View tabIndicator = LayoutInflater.from(this).inflate(R.layout.knowledge_net_tab_text, getTabWidget(), false);
        TextView title = (TextView) tabIndicator.findViewById(R.id.title);
        title.setText(labelId);
        spec.setIndicator(tabIndicator);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }

    private void addTab(String labelId, Class<?> c) {
        addTab(labelId, c, "net", net);
    }

    private void get_datas() {
        new KCAsyncTask<Void>(this){

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                net = DataProvider.get_knowledge_net(knowledge_net_id);
                net.get_tutorial_list();
                net.get_knowledge_point_list();
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
        tabHost = getTabHost();
        setTabs();
        tv_title.setText(net.get_name());
        tv_description.setText(net.get_desc());
    }
}