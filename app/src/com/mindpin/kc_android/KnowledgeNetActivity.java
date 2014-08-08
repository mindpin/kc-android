package com.mindpin.kc_android;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgeNet;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgePoint;
import com.mindpin.kc_android.models.ui_mock.UIMockTutorial;
import com.mindpin.kc_android.network.DataProvider;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dd on 14-8-7.
 */
public class KnowledgeNetActivity extends TabActivity {
    private UIMockKnowledgeNet net;
    private List<ITutorial> tutorial_list;
    private List<IKnowledgePoint> point_list;
    TabHost tabHost;
    TextView tv_description, tv_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knowledge_net);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_description = (TextView) findViewById(R.id.tv_description);
        // 页面显示这个方法返回的数据
        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        // net_id 没有实际作用，任意写一个字符串就可以
        net = (UIMockKnowledgeNet) DataProvider.get_knowledge_net("1");
//        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
//        // 教程列表
//        tutorial_list = net.get_tutorial_list();
//        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
//        // 知识点列表
//        point_list = net.get_knowledge_point_list();

        tabHost = getTabHost();
        setTabs();
        tv_title.setText(net.get_name());
        tv_description.setText(net.get_desc());
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

}