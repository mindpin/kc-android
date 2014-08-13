package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mindpin.kc_android.adapter.KnowledgeNetTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgePoint;
import com.mindpin.kc_android.models.ui_mock.UIMockTutorial;

import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetTutorialsActivity extends Activity {
    ListView listView;
    UIMockKnowledgeNet net;
    List<ITutorial> list;
    KnowledgeNetTutorialListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_tutorials);
        listView = (ListView) findViewById(R.id.lv_list);
        net = (UIMockKnowledgeNet) getIntent().getExtras().get("net");
        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        // 教程列表
        list = net.get_tutorial_list();
        adapter = new KnowledgeNetTutorialListAdapter(this);
        adapter.add_items(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                UIMockTutorial uiMockTutorial = (UIMockTutorial) parent.getItemAtPosition(position);
                String strID = uiMockTutorial.get_id();
            }
        });
    }
}