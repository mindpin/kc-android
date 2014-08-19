package com.mindpin.kc_android.activity.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.mindpin.kc_android.R;

import roboguice.fragment.RoboFragment;

/**
 * Created by fushang318 on 2014/8/19.
 */
public class KnowledgeBaseFragment extends RoboFragment{
    protected void switch_fragment(Fragment fragment){
        Bundle bundle = new Bundle();
        switch_fragment(fragment, bundle);
    }

    protected void switch_fragment(Fragment fragment, Bundle args){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        fragment.setArguments(args);
        transaction.replace(R.id.frame_layout_content, fragment, "net");
        transaction.addToBackStack("net");
        transaction.commit();
    }
}
