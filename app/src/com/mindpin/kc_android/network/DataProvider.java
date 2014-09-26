package com.mindpin.kc_android.network;


import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mindpin.android.authenticator.RequestResult;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.ITopicData;
import com.mindpin.kc_android.models.http.Note;
import com.mindpin.kc_android.models.http.Question;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.INote;
import com.mindpin.kc_android.models.interfaces.IQuestion;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/5.
 */
public class DataProvider {
    /**
     *
     * 1 KnowledgeNetListActivity 使用数据
     * 获取所有知识网络
     * @return 知识网络列表
     */
    public static List<IKnowledgeNet> get_knowledge_net_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_knowledge_net_list();
    }

    /**
     * 2 KnowledgeNetActivity 使用数据
     * 获取某个知识网络
     * @param knowledge_net_id 服务器端的知识网络id
     * @return 知识网络
     */
    public static IKnowledgeNet get_knowledge_net(String knowledge_net_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_knowledge_net(knowledge_net_id);
    }

    /**
     * 3 KnowledgePointActivity 使用数据
     * @param knowledge_point_id 服务器端的知识节点id
     * @return
     */
    public static IKnowledgePoint get_knowledge_point(String knowledge_point_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_knowledge_point(knowledge_point_id);
    }

    /**
     * 4 TutorialActivity 使用数据
     * 获取某个教程的详细内容
     * @param tutorial_id 服务器端的教程id
     * @return 教程
     */
    public static ITutorial get_tutorial(String tutorial_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_tutorial(tutorial_id);
    }

    /**
     * 4 TutorialActivity 使用数据
     * 获取某个教程步骤的详细内容
     * @param step_id
     * @return 教程步骤
     * @throws HttpApi.RequestDataErrorException
     * @throws HttpApi.AuthErrorException
     * @throws HttpApi.NetworkErrorException
     */
    public static IStep get_step(String step_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_step(step_id);
    }


    public static List<ITopic> get_topic_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_topic_list();
    }

    public static List<ITutorial> get_topic_tutorial_list(String topic_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_topic_tutorial_list(topic_id);
    }

    public static ITopic get_topic(String topic_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_topic(topic_id);
    }

    public static boolean learn_step(String step_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.learn_step(step_id);
    }


    // 创建问题
    public static IQuestion create_question(String step_id, String content) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.create_question(step_id,content);
    }

    // 查询问题
    public static IQuestion get_question(String question_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_question(question_id);
    }

    // 修改问题
    public static IQuestion edit_question(String question_id, String content) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.edit_question(question_id, content);
    }
    // 删除问题
    public static boolean destroy_question(String question_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.destroy_question(question_id);
    }

    // 创建笔记
    public static INote create_note(String step_id, String content) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.create_note(step_id, content);
    }

    // 查询笔记
    public static INote get_note(String note_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_note(note_id);
    }

    // 修改笔记
    public static INote edit_note(String note_id, String content) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.edit_note(note_id, content);
    }
    // 删除笔记
    public static boolean destroy_note(String note_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.destroy_note(note_id);
    }
    public static boolean set_step_hard(String step_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.set_step_hard(step_id);
    }

    public static boolean unset_step_hard(String step_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.unset_step_hard(step_id);
    }

    public static List<IStep> get_my_step_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_my_step_list();
    }

    public static List<IQuestion> get_my_question_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_my_question_list();
    }

    public static List<INote> get_my_note_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_my_note_list();
    }

    public static List<ITopic> get_my_topic_list() throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_my_topic_list();
    }

}
