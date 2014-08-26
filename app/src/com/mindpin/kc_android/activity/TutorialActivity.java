package com.mindpin.kc_android.activity;

import android.os.Bundle;
import android.util.Log;

import com.mindpin.kc_android.activity.base.KnowledgeBaseActivity;
import com.mindpin.kc_android.models.interfaces.ITutorial;


public class TutorialActivity extends KnowledgeBaseActivity {
    private ITutorial tutorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tutorial = (ITutorial)getIntent().getSerializableExtra("tutorial");

        Log.d("debug!!", tutorial.get_id());
        Log.d("debug!!", tutorial.get_title());
        Log.d("debug!!", tutorial.get_desc());
        Log.d("debug!!", tutorial.get_icon_url());
    }
}
