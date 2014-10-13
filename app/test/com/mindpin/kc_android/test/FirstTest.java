package com.mindpin.kc_android.test;

import com.mindpin.android.authenticator.AuthCallback;
import com.mindpin.android.authenticator.IUser;
import com.mindpin.kc_android.application.KCApplication;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.User;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.INote;
import com.mindpin.kc_android.models.interfaces.IQuestion;
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


//@RunWith(RobolectricTestRunner.class)
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
//        String knowledge_net_id = "5423a33a6c696e66a1000000";
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
//        String knowledge_point_id = "5423a33a6c696e66a1060000";
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
//        String tutorial_id = "5423a3ad6c696e66a16c0100";
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
//        String stutorial_id = "5423a3ad6c696e66a16c0100";
//        List<IStep> step_list = HttpApi.get_step_list(stutorial_id);
//
//        System.out.println(step_list.size());
//        System.out.println(step_list.get(0).get_title());
//
//        IStep step = HttpApi.get_step(step_list.get(0).get_id());
//        System.out.println(step.get_title());
//        System.out.println(step.get_id());
//
//        System.out.println("~~~~~~~~~~~~~~~~~~~~~ steps begin");
//
//        for(IStep s : step_list){
//            System.out.println(">>>>");
//            System.out.println(s.get_continue_type());
//            if(s.get_continue_type() == IStep.ContinueType.STEP){
//                System.out.println("STEP TYPE");
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
//        String meishu_net_id = "5423a3406c696e66a1250000";
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
//        String pc_tutorial_id = "5423a3b66c696e66a17e0100";
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
//
//
//        String sstep_id = "5423a4216c696e66a1ee0200";
//        HttpApi.learn_step(sstep_id);
//
//        System.out.println("~~~~~~~~~~~~~ get_step_list begin ~~~~~~~~~~~~~");
//        String ntutorial_id = "5423a3b66c696e66a17e0100";
//        List<IStep> sstep_list = HttpApi.get_step_list(ntutorial_id);
//
//        for(IStep sstep: sstep_list){
//            System.out.println(sstep.get_title());
//
//            List<IStep.IContentBlock> blocks = sstep.get_blocks();
//            for(IStep.IContentBlock block : blocks){
//                if(block.get_kind() == IStep.IContentBlock.ContentKind.TEXT){
//                    System.out.println("IStep.IContentBlock.ContentKind.TEXT");
//                    System.out.println(block.get_content());
//                }
//                if(block.get_kind() == IStep.IContentBlock.ContentKind.IMAGE){
//                    System.out.println("IStep.IContentBlock.ContentKind.IMAGE");
//                    System.out.println(block.get_url());
//                }
//                if(block.get_kind() == IStep.IContentBlock.ContentKind.VIDEO){
//                    System.out.println("IStep.IContentBlock.ContentKind.VIDEO");
//                    System.out.println(block.get_url());
//                }
//            }
//        }
//
//        System.out.println("~~~~~~~~~~~~~ step question ~~~~~~~~~~~~~");
//        step = sstep_list.get(0);
//
//        IQuestion question = HttpApi.create_question(step.get_id(), "我是问题1");
//        IQuestion question1 = HttpApi.get_question(question.get_id());
//        System.out.println(question1.get_content());
//
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.has_question());
//
//        HttpApi.edit_question(question.get_id(), "我是问题1改");
//        IQuestion question2 = HttpApi.get_question(question.get_id());
//        System.out.println(question2.get_content());
//
//        HttpApi.destroy_question(question.get_id());
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.has_question());
//
//        System.out.println("~~~~~~~~~~~~~ step note ~~~~~~~~~~~~~");
//        step = sstep_list.get(0);
//
//        INote note = HttpApi.create_note(step.get_id(), "我是笔记1");
//        INote note1 = HttpApi.get_note(note.get_id());
//        System.out.println(note1.get_content());
//
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.has_note());
//
//        HttpApi.edit_note(note.get_id(), "我是笔记1改");
//        INote note2 = HttpApi.get_note(note.get_id());
//        System.out.println(note2.get_content());
//
//        HttpApi.destroy_note(note.get_id());
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.has_note());
//
//        System.out.println("~~~~~~~~~~~~~ step hard ~~~~~~~~~~~~~");
//        System.out.println(step.is_hard());
//
//        HttpApi.set_step_hard(step.get_id());
//
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.is_hard());
//
//        HttpApi.unset_step_hard(step.get_id());
//
//        sstep_list = HttpApi.get_step_list(ntutorial_id);
//        step = sstep_list.get(0);
//        System.out.println(step.is_hard());
//
//
//        System.out.println("~~~~~~~~~~~~~ my topic ~~~~~~~~~~~~~");
//        topic_list = HttpApi.get_my_topic_list();
//        System.out.println("topic_list count " + topic_list.size());
//        ITopic topic = topic_list.get(0);
//        System.out.println(topic.get_title());
//
//        System.out.println("~~~~~~~~~~~~~ my step ~~~~~~~~~~~~~");
//        System.out.println(step.get_id());
//        HttpApi.set_step_hard(step.get_id());
//        step_list = HttpApi.get_my_step_list();
//        System.out.println("step_list count " + step_list.size());
//        step = step_list.get(0);
//        System.out.println(step.get_id());
//        System.out.println("tutorial_id " + step.get_tutorial().get_id());
//
//        System.out.println("~~~~~~~~~~~~~ my question ~~~~~~~~~~~~~");
//        question = HttpApi.create_question(step.get_id(),"问题");
//        question2 = HttpApi.create_question(sstep_list.get(1).get_id(),"问题2");
//
//        List<IQuestion> question_list = HttpApi.get_my_question_list();
//        System.out.println("count " + question_list.size());
//
//        System.out.println(question_list.get(0).get_content());
//        System.out.println("tutorial_id " + question_list.get(0).get_tutorial().get_id());
//
//        HttpApi.destroy_question(question.get_id());
//        HttpApi.destroy_question(question2.get_id());
//        System.out.println("~~~~~~~~~~~~~ my note ~~~~~~~~~~~~~");
//        note = HttpApi.create_note(step.get_id(), "笔记");
//        note2 = HttpApi.create_note(sstep_list.get(1).get_id(), "笔记2");
//
//        List<INote> note_list = HttpApi.get_my_note_list();
//
//        System.out.println("count " + note_list.size());
//        System.out.println(note_list.get(0).get_content());
//        System.out.println("tutorial_id " + note_list.get(0).get_tutorial().get_id());
//
//        HttpApi.destroy_note(note.get_id());
//        HttpApi.destroy_note(note2.get_id());
        ////
    }

}

