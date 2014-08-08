package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.mindpin.kc_android.adapter.UIMockTutorialAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;

import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetTutorialsActivity extends Activity {
    private static final String TAG = "KnowledgeNetTutorialsActivity";
    ListView listView;
    UIMockKnowledgeNet net;
    List<ITutorial> list;
    UIMockTutorialAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_tutorials);
        listView = (ListView) findViewById(R.id.lv_list);
        net = (UIMockKnowledgeNet)getIntent().getExtras().get("net");
        list = net.get_tutorial_list();
        adapter = new UIMockTutorialAdapter(this);
//        adapter.inflate_view();
        adapter.add_items(list);
        listView.setAdapter(adapter);
        Log.e(TAG, "net:" + net);
        Log.e(TAG, "list size:" + list.size());
    }
}