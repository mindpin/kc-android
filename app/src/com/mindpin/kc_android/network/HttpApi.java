package com.mindpin.kc_android.network;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mindpin.android.authenticator.RequestResult;
import com.mindpin.kc_android.R;
import com.mindpin.kc_android.application.KCApplication;
import com.mindpin.kc_android.controllers.AuthenticatorsController;
import com.mindpin.kc_android.models.http.KnowledgeNet;
import com.mindpin.kc_android.models.http.KnowledgePoint;
import com.mindpin.kc_android.models.http.Step;
import com.mindpin.kc_android.models.http.Topic;
import com.mindpin.kc_android.models.http.Tutorial;
import com.mindpin.kc_android.models.interfaces.IKnowledgeNet;
import com.mindpin.kc_android.models.interfaces.IKnowledgePoint;
import com.mindpin.kc_android.models.interfaces.IStep;
import com.mindpin.kc_android.models.interfaces.ITopic;
import com.mindpin.kc_android.models.interfaces.ITutorial;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by fushang318 on 2014/8/11.
 */
public class HttpApi {
    /**
     * http api url begin
     */
    public static final String SITE = KCApplication.get_context().getResources().getString(R.string.http_site);

    public static final String 获取知识网络列表 = SITE + "/api/nets.json";

    public static final String 标记学习步骤为已学 = SITE + "/api/learn_records.json";

    public static String 获取知识网络(String knowledge_net_id){
        return SITE + String.format("/api/nets/%s.json", knowledge_net_id);
    }

    public static String 获取知识点列表(String knowledge_net_id){
        return SITE + String.format("/api/points.json?net_id=%s", knowledge_net_id);
    }

    public static String 获取知识网络的教程列表(String knowledge_net_id){
        return SITE + String.format("/api/tutorials.json?net_id=%s", knowledge_net_id);
    }

    public static String 获取知识点(String knowledge_point_id){
        return SITE + String.format("/api/points/%s.json", knowledge_point_id);
    }

    public static String 获取知识点相关联的教程列表(String knowledge_point_id){
        return SITE + String.format("/api/tutorials.json?point_id=%s", knowledge_point_id);
    }

    public static String 获取教程(String tutorial_id){
        return SITE + String.format("/api/tutorials/%s.json", tutorial_id);
    }

    public static String 获取教程相关联的知识点列表(String tutorial_id){
        return SITE + String.format("/api/points.json?tutorial_id=%s", tutorial_id);
    }

    public static String 获取教程的步骤列表(String tutorial_id){
        return SITE + String.format("/api/steps.json?tutorial_id=%s", tutorial_id);
    }
    public static String 获取教程步骤(String step_id){
        return SITE + String.format("/api/steps/%s.json", step_id);
    }
    public static final String 获取主题列表 = SITE + "/api/topics.json";
    public static String 获取知识网络的主题列表(String knowledge_net_id){
        return SITE + String.format("/api/topics.json?net_id=%s", knowledge_net_id);
    }
    public static String 获取主题的教程列表(String topic_id){
        return SITE + String.format("/api/tutorials.json?topic_id=%s", topic_id);
    }
    public static String 获取教程的前置教程列表(String tutorial_id){
        return SITE + String.format("/api/tutorials.json?child_id=%s", tutorial_id);
    }
    public static String 获取教程的后续教程列表(String tutorial_id){
        return SITE + String.format("/api/tutorials.json?parent_id=%s", tutorial_id);
    }
    /**
     * http api url end
     */


    public static List<IKnowledgeNet> get_knowledge_net_list() throws AuthErrorException, RequestDataErrorException, NetworkErrorException {
        return new RequestProcess<List<IKnowledgeNet>>(){

            @Override
            public List<IKnowledgeNet> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<KnowledgeNet>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(获取知识网络列表, "GET");
            }
        }.request();
    }
    //////////////////

    public static IKnowledgeNet get_knowledge_net(final String knowledge_net_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<IKnowledgeNet>(){

            @Override
            public IKnowledgeNet call(RequestResult rr) {
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(rr.body, KnowledgeNet.class);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识网络(knowledge_net_id),"GET");
            }
        }.request();
    }

    public static List<IKnowledgePoint> get_net_knowledge_point_list(final String knowledge_net_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<IKnowledgePoint>>(){

            @Override
            public List<IKnowledgePoint> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<KnowledgePoint>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识点列表(knowledge_net_id), "GET");
            }
        }.request();
    }

    public static List<ITutorial> get_net_tutorial_list(final String knowledge_net_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITutorial>>(){

            @Override
            public List<ITutorial> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Tutorial>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识网络的教程列表(knowledge_net_id), "GET");
            }
        }.request();
    }
    /////////////////////////////////

    public static IKnowledgePoint get_knowledge_point(final String knowledge_point_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<IKnowledgePoint>(){

            @Override
            public IKnowledgePoint call(RequestResult rr) {
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(rr.body, KnowledgePoint.class);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识点(knowledge_point_id), "GET");
            }
        }.request();
    }

    public static List<ITutorial> get_point_tutorial_list(final String knowledge_point_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITutorial>>(){

            @Override
            public List<ITutorial> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Tutorial>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识点相关联的教程列表(knowledge_point_id), "GET");
            }
        }.request();
    }
    /////////////////////////////////


    public static ITutorial get_tutorial(final String tutorial_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<ITutorial>(){

            @Override
            public ITutorial call(RequestResult rr) {
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(rr.body, Tutorial.class);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程(tutorial_id), "GET");
            }
        }.request();
    }

    public static List<IKnowledgePoint> get_tutorial_knowledge_point_list(final String tutorial_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<IKnowledgePoint>>(){

            @Override
            public List<IKnowledgePoint> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<KnowledgePoint>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程相关联的知识点列表(tutorial_id), "GET");
            }
        }.request();
    }

    public static List<IStep> get_step_list(final String tutorial_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<IStep>>(){

            @Override
            public List<IStep> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Step>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程的步骤列表(tutorial_id), "GET");
            }
        }.request();
    }

    public static IStep get_step(final String step_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<IStep>(){

            @Override
            public IStep call(RequestResult rr) {
                Gson gson = new GsonBuilder().create();
                return gson.fromJson(rr.body, Step.class);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程步骤(step_id), "GET");
            }
        }.request();
    }

    public static List<ITopic> get_topic_list() throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITopic>>(){

            @Override
            public List<ITopic> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Topic>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取主题列表, "GET");
            }
        }.request();
    }

    public static List<ITopic> get_net_topic_list(final String knowledge_net_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITopic>>(){

            @Override
            public List<ITopic> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Topic>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取知识网络的主题列表(knowledge_net_id), "GET");
            }
        }.request();
    }

    public static List<ITutorial> get_topic_tutorial_list(final String topic_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITutorial>>(){

            @Override
            public List<ITutorial> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Tutorial>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取主题的教程列表(topic_id), "GET");
            }
        }.request();
    }

    public static List<ITutorial> get_parent_tutorial_list(final String tutorial_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITutorial>>(){

            @Override
            public List<ITutorial> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Tutorial>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程的前置教程列表(tutorial_id), "GET");
            }
        }.request();
    }

    public static List<ITutorial> get_child_tutorial_list(final String tutorial_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<List<ITutorial>>(){

            @Override
            public List<ITutorial> call(RequestResult rr) {
                Type collectionType = new TypeToken<List<Tutorial>>(){}.getType();
                Gson gson = new Gson();
                return gson.fromJson(rr.body, collectionType);
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.获取教程的后续教程列表(tutorial_id), "GET");
            }
        }.request();
    }

    public static boolean learn_step(final String step_id) throws RequestDataErrorException, AuthErrorException, NetworkErrorException {
        return new RequestProcess<Boolean>(){

            @Override
            public Boolean call(RequestResult rr) {
                return true;
            }

            @Override
            public HttpRequest build_request(AuthenticatorsController auth) {
                return auth.get_http_request(HttpApi.标记学习步骤为已学, "POST").part("learn_record[step_id]", step_id);
            }
        }.request();
    }
    /**
     * http api method end
     ***********************************************************************************/


    public abstract static class RequestProcess<T>{
        public T request() throws AuthErrorException, RequestDataErrorException, NetworkErrorException{
            AuthenticatorsController auth = new AuthenticatorsController(KCApplication.get_context());
            HttpRequest request = build_request(auth);
            RequestResult rr = auth.syn_request(request);
            if(rr == null) throw new NetworkErrorException();
            if(rr.status == 200){
                return call(rr);
            }else if(rr.status == 401){
                throw new AuthErrorException();
            }else{
                throw new RequestDataErrorException();
            }
        };

        public abstract T call(RequestResult rr);

        public abstract HttpRequest build_request(AuthenticatorsController auth);
    }

    public static class AuthErrorException extends Exception{};
    public static class RequestDataErrorException extends Exception{};
    public static class NetworkErrorException extends Exception{};
}
