package com.mindpin.kc_android.test;

import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.network.DataProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

/**
 * Created by fushang318 on 2014/8/8.
 */


@RunWith(RobolectricTestRunner.class)
public class FirstTest  {
    @Before
    public void setUp() throws Exception {
        System.out.println("set up");
    }

    @Test
    public void t_1(){
        List<IKnowledgeNet> list = DataProvider.get_knowledge_net_list();
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.get(0).get_name());
    }
}
