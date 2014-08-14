package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mindpin.kc_android.adapter.KnowledgeNetTutorialListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetTutorialsActivity extends Activity {
    ListView listView;
    IKnowledgeNet net;
    List<ITutorial> list;
    KnowledgeNetTutorialListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_tutorials);
        listView = (ListView) findViewById(R.id.lv_list);
        net = (IKnowledgeNet) getIntent().getExtras().get("net");
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
                ITutorial tutorial = (ITutorial) parent.getItemAtPosition(position);
                String tutorial_id = tutorial.get_id();

                Intent intent = new Intent(KnowledgeNetTutorialsActivity.this, TutorialActivity.class);
                intent.putExtra("tutorial_id", tutorial_id);
                startActivity(intent);
            }
        });
    }
}