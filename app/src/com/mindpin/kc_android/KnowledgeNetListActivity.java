package com.mindpin.kc_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mindpin.android.loadingview.LoadingView;
import com.mindpin.kc_android.adapter.KnowledgeNetListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.network.DataProvider;
import com.mindpin.kc_android.utils.KCAsyncTask;

import java.util.List;

import roboguice.activity.RoboActivity;


public class KnowledgeNetListActivity extends RoboActivity {
    private List<IKnowledgeNet> net_list;
    private LoadingView loading_view;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_list_activity);

        Log.i("启动 ", "true");

        loading_view = (LoadingView) findViewById(R.id.loading_view);
        get_datas();
    }


    private void get_datas() {

        new KCAsyncTask<Void>(this){

            @Override
            protected void onPreExecute() throws Exception {
                loading_view.show();
            }

            @Override
            public Void call() throws Exception {
                net_list = DataProvider.get_knowledge_net_list();
                return null;
            }

            @Override
            protected void onSuccess(Void aVoid) throws Exception {
                build_view();
                loading_view.hide();
            }
        }.execute();
    }

    private void build_view(){
        ListView listview = (ListView) this.findViewById(R.id.knowledge_net_list);

        KnowledgeNetListAdapter adapter =
                new KnowledgeNetListAdapter(this, R.id.knowledge_net_list, net_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");

                IKnowledgeNet knowledge_net =
                        (IKnowledgeNet) parent.getItemAtPosition(position);

                String knowledge_net_id = knowledge_net.get_id();
                Log.i("knowledge_net_id ID ", knowledge_net_id);

                Intent intent = new Intent(KnowledgeNetListActivity.this, KnowledgeNetActivity.class);
                intent.putExtra("knowledge_net_id", knowledge_net_id);
                startActivity(intent);
            }
        });
    }
}