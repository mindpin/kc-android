package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgePoint;

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
        net = (UIMockKnowledgeNet) getIntent().getExtras().get("net");
        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        // 知识点列表
        list = net.get_knowledge_point_list();
        adapter = new KnowledgeNetPointListAdapter(this);
        adapter.add_items(list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                UIMockKnowledgePoint uiMockKnowledgePoint = (UIMockKnowledgePoint) parent.getItemAtPosition(position);
                String strID = uiMockKnowledgePoint.get_id();
            }
        });
    }
}