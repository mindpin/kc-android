package com.mindpin.kc_android.network;


import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.ITutorial;

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
     * 3 TutorialActivity 使用数据
     * 4 TutorialStepActivity 使用数据
     * 5 TutorialEndActivity 使用数据
     * 获取某个教程的详细内容
     * @param tutorial_id 服务器端的教程id
     * @return 教程
     */
    public static ITutorial get_tutorial(String tutorial_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_tutorial(tutorial_id);
    }


    /**
     * 6 KnowledgePointActivity 使用数据
     * @param knowledge_point_id 服务器端的知识节点id
     * @return
     */
    public static IKnowledgePoint get_knowledge_point(String knowledge_point_id) throws HttpApi.RequestDataErrorException, HttpApi.AuthErrorException, HttpApi.NetworkErrorException {
        return HttpApi.get_knowledge_point(knowledge_point_id);
    }

}
