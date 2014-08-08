package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.mindpin.kc_android.adapter.KnowledgeNetListAdapter;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.UIMockTutorialAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;

import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetPointsActivity extends Activity {
    ListView listView;
    UIMockKnowledgeNet net;
    List<IKnowledgePoint> list;
    KnowledgeNetPointListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_points);
        listView = (ListView) findViewById(R.id.lv_list);
        net = (UIMockKnowledgeNet)getIntent().getExtras().get("net");
        list = net.get_knowledge_point_list();
        adapter = new KnowledgeNetPointListAdapter(this);
        adapter.add_items(list);
        listView.setAdapter(adapter);
    }
}