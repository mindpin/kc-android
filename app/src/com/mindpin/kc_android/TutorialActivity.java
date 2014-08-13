package com.mindpin.kc_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.github.destinyd.FlipBriefLayout;
import com.mindpin.kc_android.adapter.KnowledgeNetPointListAdapter;
import com.mindpin.kc_android.adapter.TutorialStepListAdapter;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.ui_mock.UIMockKnowledgePoint;
import com.mindpin.kc_android.models.ui_mock.UIMockTutorial;
import com.mindpin.kc_android.network.DataProvider;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dd on 14-8-11.
 */
public class TutorialActivity extends Activity {

    private static final String TAG = "TutorialActivity";
    // brief
//    @InjectView(R.id.lv_list_brief)
    protected ListView lv_list_brief;
//    @InjectView(R.id.tv_description_brief)
    protected TextView tv_description_brief;
//    @InjectView(R.id.iv_cover)
    protected ImageView iv_cover;

    //detail
//    @InjectView(R.id.lv_list_detail)
    protected ListView lv_list_detail;
//    @InjectView(R.id.tv_title_detail)
    protected TextView tv_title_detail;
//    @InjectView(R.id.tv_description_detail)
    protected TextView tv_description_detail;

    View brief;
    View detail;
    UIMockTutorial tutorial;
    FlipBriefLayout kcFlip;
    KnowledgeNetPointListAdapter adapter_brief;
    TutorialStepListAdapter adapter_detail;
    List<IKnowledgePoint> point_list;
    List<IStep> step_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial);
        kcFlip = (FlipBriefLayout) findViewById(R.id.kcflip);

        get_and_add_layouts_to_flip();
        find_views();
        get_datas();

        datas_to_brief();
        datas_to_detail();
    }

    private void datas_to_brief() {
        tv_description_brief.setText(tutorial.get_desc());
        ImageLoader.getInstance().displayImage(tutorial.get_icon_url(), iv_cover);
        adapter_brief = new KnowledgeNetPointListAdapter(this);
        adapter_brief.add_items(point_list);
        lv_list_brief.setAdapter(adapter_brief);
        lv_list_brief.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //这次设置点击跳转
                UIMockKnowledgePoint uiMockKnowledgePoint = (UIMockKnowledgePoint) parent.getItemAtPosition(position);
                String strID = uiMockKnowledgePoint.get_id();
            }
        });
    }

    private void datas_to_detail() {
        tv_title_detail.setText(tutorial.get_title());
        tv_description_detail.setText(tutorial.get_desc());
        adapter_detail = new TutorialStepListAdapter(this);
        adapter_detail.add_items(step_list);
        lv_list_detail.setAdapter(adapter_detail);
    }


    private void get_datas() {
        // 页面显示这个方法返回的数据
        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        // tutorial_id 随便传递一个字符串即可，不影响制造夹具数据
        tutorial = (UIMockTutorial) DataProvider.get_tutorial("test");

        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        point_list = tutorial.get_related_knowledge_point_list();

        // 这个方法内部通过硬编码制造夹具数据（硬编码制造夹具数据也是任务的一部分）
        step_list = tutorial.get_step_list();
    }

    private void get_and_add_layouts_to_flip() {
        brief = LayoutInflater.from(this).inflate(R.layout.tutorial_brief, null);
        detail = LayoutInflater.from(this).inflate(R.layout.tutorial_detail, null);
        kcFlip.set_brief_view(brief);
        kcFlip.set_detail_view(detail);
    }

    private void find_views() {
        find_brief_views();
        find_detail_views();
    }

    private void find_detail_views() {
        lv_list_detail = (ListView)detail.findViewById(R.id.lv_list_detail);
        tv_title_detail = (TextView)detail.findViewById(R.id.tv_title_detail);
        tv_description_detail = (TextView)detail.findViewById(R.id.tv_description_detail);
    }

    private void find_brief_views() {
        lv_list_brief = (ListView)brief.findViewById(R.id.lv_list_brief);
        tv_description_brief = (TextView)brief.findViewById(R.id.tv_description_brief);
        iv_cover = (ImageView)brief.findViewById(R.id.iv_cover);
    }

    @Override
    protected void onResume() {
        super.onResume();
        kcFlip.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        kcFlip.onPause();
    }
}