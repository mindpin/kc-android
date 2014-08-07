package com.mindpin.kc_android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.DataProvider;

import java.util.List;


public class KnowledgeNetListActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net_list_activity);

        Log.i("启动 ", "true");

        List<IKnowledgeNet> net_list = DataProvider.get_knowledge_net_list();

        ListView listView = (ListView) this.findViewById(R.id.knowledge_net_list);

        KnowledgeNetListAdapter adapter =
                new KnowledgeNetListAdapter(this, R.id.knowledge_net_list, net_list);
        listView.setAdapter(adapter);
    }



}