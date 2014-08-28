package com.mindpin.kc_android.test;

import com.mindpin.android.authenticator.AuthCallback;
import com.mindpin.android.authenticator.IUser;
import com.mindpin.kc_android.application.KCApplication;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;
import com.mindpin.kc_android.network.HttpApi;

import org.apache.tools.ant.taskdefs.condition.Http;
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
//        System.out.println("set up begin");
//        User.delete_all();
//        System.out.println("1 " + KCApplication.get_context());
//        AuthenticatorsController ac = new AuthenticatorsController(KCApplication.get_context());
//        final boolean[] wait = new boolean[]{true};
//        ac.sign_in("user1","1234", new AuthCallback() {
//            @Override
//            public void success(IUser user) {
//                wait[0] = false;
//            }
//
//            @Override
//            public void failure() {
//                wait[0] = false;
//            }
//
//            @Override
//            public void error() {
//                wait[0] = false;
//            }
//        });
//        while(wait[0]){}
//        System.out.println(ac.current_user());
//        System.out.println("set up end");
    }

    @Test
    public void t_1() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
//        List<IKnowledgeNet> list = HttpApi.get_knowledge_net_list();
//        System.out.println(list);
//        System.out.println(list.size());
//        System.out.println(list.get(1).get_name());
//        System.out.println(list.get(1).get_desc());
//        System.out.println(list.get(1).get_point_count());
//
//        IKnowledgeNet net = HttpApi.get_knowledge_net(list.get(1).get_id());
//        System.out.println(net.get_name());
//        System.out.println(net.get_desc());
//        System.out.println(net.get_point_count());
//
//
//        List<IKnowledgePoint> point_list = HttpApi.get_net_knowledge_point_list(list.get(1).get_id());
//        System.out.println(point_list.size());
//
//        System.out.println(point_list.get(0).get_name());
//        System.out.println(point_list.get(0).get_desc());
//
//        String knowledge_net_id = "53fd896c6c696e1615000000";
//        IKnowledgeNet snet = HttpApi.get_knowledge_net(knowledge_net_id);
//
//        System.out.println(snet.get_name());
//        System.out.println(snet.get_desc());
//        System.out.println(snet.get_point_count());
//
//
//        List<IKnowledgePoint> spoint_list = HttpApi.get_net_knowledge_point_list(knowledge_net_id);
//        System.out.println(spoint_list.size());
//        System.out.println(spoint_list.get(0).get_name());
//        System.out.println(spoint_list.get(0).get_desc());
//
//
//        List<ITutorial> tutorial_list = HttpApi.get_net_tutorial_list(knowledge_net_id);
//
//        System.out.println(tutorial_list.size());
//        System.out.println(tutorial_list.get(0).get_title());
//        System.out.println(tutorial_list.get(0).get_desc());
//
//
//        String knowledge_point_id = "53fd896d6c696e16150d0000";
//        IKnowledgePoint point = HttpApi.get_knowledge_point(knowledge_point_id);
//
//        System.out.println(point.get_id());
//        System.out.println(point.get_name());
//        System.out.println(point.get_desc());
//
//
//        List<ITutorial> stutorial_list = HttpApi.get_point_tutorial_list(knowledge_point_id);
//
//        System.out.println(stutorial_list.size());
//        System.out.println(stutorial_list.get(0).get_title());
//        System.out.println(stutorial_list.get(0).get_desc());
//
//
//        String tutorial_id = "53fd89cf6c696e16156e0100";
//        ITutorial tutorial = HttpApi.get_tutorial(tutorial_id);
//
//        System.out.println(tutorial.get_title());
//        System.out.println(tutorial.get_desc());
//
//
//        List<IKnowledgePoint> tpoint_list = HttpApi.get_tutorial_knowledge_point_list(tutorial.get_id());
//
//        System.out.println(tpoint_list.size());
//        System.out.println(tpoint_list.get(0).get_name());
//        System.out.println(tpoint_list.get(0).get_desc());
//
//        String stutorial_id = "53fd89db6c696e1615bc0100";
//        List<IStep> step_list = HttpApi.get_step_list(stutorial_id);
//
//        System.out.println(step_list.size());
//        System.out.println(step_list.get(0).get_title());
//        System.out.println(step_list.get(0).get_desc());
//
//        IStep step = HttpApi.get_step(step_list.get(0).get_id());
//        System.out.println(step.get_title());
//        System.out.println(step.get_desc());
//        System.out.println(step.get_id());
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ steps begin");
//
//        for(IStep s : step_list){
//            System.out.println(">>>>");
//            System.out.println(s.get_continue_type());
//            if(s.get_continue_type() == IStep.ContinueType.ID){
//                System.out.println("ID TYPE");
//                System.out.println(s.get_next_id());
//            }
//            if(s.get_continue_type() == IStep.ContinueType.END){
//                System.out.println("END TYPE");
//                System.out.println(s.is_end());
//            }
//            if(s.get_continue_type() == IStep.ContinueType.SELECT){
//                System.out.println("SELECT TYPE");
//                IStep.ISelect select = s.get_select();
//                System.out.println(select.get_question());
//                List<IStep.ISelectOption> so_list = select.get_select_options();
//                for(IStep.ISelectOption so : so_list){
//                    System.out.println(so.get_next_step_id());
//                    System.out.println(so.get_text());
//                }
//            }
//        }
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ steps end");
//
//        List<ITopic> topic_list = HttpApi.get_topic_list();
//
//        System.out.println("topics count is " + topic_list.size());
//
//        for(ITopic topic : topic_list){
//            System.out.println(topic.get_id());
//            System.out.println(topic.get_title());
//            System.out.println(topic.get_desc());
//            System.out.println(topic.get_tutorial_count());
//        }
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ get_net_topic_list start");
//
//        String meishu_net_id = "53fd89726c696e1615240000";
//        List<ITopic> net_topic_list = HttpApi.get_net_topic_list(meishu_net_id);
//        System.out.println("topics count is " + net_topic_list.size());
//
//        for(ITopic topic : net_topic_list){
//            System.out.println(topic.get_id());
//            System.out.println(topic.get_title());
//            System.out.println(topic.get_desc());
//            System.out.println(topic.get_tutorial_count());
//        }
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ get_topic_tutorial_list start");
//
//        List<ITutorial> t_list = HttpApi.get_topic_tutorial_list(net_topic_list.get(net_topic_list.size() - 1).get_id());
//
//        for(ITutorial t : t_list){
//            System.out.println(t.get_id());
//            System.out.println(t.get_title());
//            System.out.println(t.get_desc());
//        }
//
//
//        String pc_tutorial_id = "53fd89cf6c696e16156e0100";
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ get_parent_tutorial_list start");
//
//        List<ITutorial> p_t_list = HttpApi.get_parent_tutorial_list(pc_tutorial_id);
//
//        for(ITutorial t : p_t_list){
//            System.out.println(t.get_id());
//            System.out.println(t.get_title());
//            System.out.println(t.get_desc());
//        }
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ get_child_tutorial_list start");
//        List<ITutorial> c_t_list = HttpApi.get_child_tutorial_list(pc_tutorial_id);
//
//        for(ITutorial t : c_t_list){
//            System.out.println(t.get_id());
//            System.out.println(t.get_title());
//            System.out.println(t.get_desc());
//        }
    }

}

