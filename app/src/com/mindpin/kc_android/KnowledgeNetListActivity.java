package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mindpin.kc_android.adapter.KnowledgeNetListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;
import com.mindpin.kc_android.models.ui_mock.UIMockTutorial;
import com.mindpin.kc_android.network.DataProvider;

import java.util.List;


public class KnowledgeNetListActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_list_activity);

        Log.i("启动 ", "true");

        List<IKnowledgeNet> net_list = DataProvider.get_knowledge_net_list();

        ListView listview = (ListView) this.findViewById(R.id.knowledge_net_list);

        KnowledgeNetListAdapter adapter =
                new KnowledgeNetListAdapter(this, R.id.knowledge_net_list, net_list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Log.i("listview 事件 ", "true");

                UIMockKnowledgeNet knowledge_net =
                        (UIMockKnowledgeNet) parent.getItemAtPosition(position);

                String knowledge_net_id = knowledge_net.get_id();

                Log.i("knowledge_net_id ID ", knowledge_net_id);
            }
        });
    }



}