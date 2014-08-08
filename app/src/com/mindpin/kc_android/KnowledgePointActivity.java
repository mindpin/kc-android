package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.network.DataProvider;


public class KnowledgePointActivity extends Activity {
    TextView knowledge_point_name;
    TextView knowledge_point_desc;
    TextView knowledge_net_name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_point_activity);

        init_view();

        String knowledge_point_id = "knowledge_point_id_for_test";
        IKnowledgePoint point = DataProvider.get_knowledge_point(knowledge_point_id);

        knowledge_point_name.setText(point.get_name());
        knowledge_point_desc.setText(point.get_desc());


        IKnowledgeNet net = point.get_knowledge_net();
        // knowledge_net_name.setText(net.get_name());

    }

    private void init_view() {
        knowledge_point_name = (TextView) findViewById(R.id.knowledge_point_name);
        knowledge_point_desc = (TextView) findViewById(R.id.knowledge_point_desc);
        knowledge_net_name = (TextView) findViewById(R.id.knowledge_net_name);
    }
}