package com.mindpin.kc_android.activity.base;

import java.util.Stack;

/**
 * Created by fushang318 on 2014/9/23.
 */

// 用于管理和回收activities的堆栈，单例模式
public class ActivitiesStackSingleton {
    private static ActivitiesStackSingleton instance = new ActivitiesStackSingleton();

    private Stack<KnowledgeBaseActivity> activities_stack;

    private ActivitiesStackSingleton() {
        activities_stack = new Stack<KnowledgeBaseActivity>();
    }

    private static Stack<KnowledgeBaseActivity> get_activities_stack() {
        return instance.activities_stack;
    }

    // 关闭所有堆栈中的activity
    protected static void clear_activities_stack() {
        Stack<KnowledgeBaseActivity> activities_stack = get_activities_stack();

        int size = activities_stack.size();
        for (int i = 0; i < size; i++) {
            KnowledgeBaseActivity activity = activities_stack.pop();
            activity.finish();
        }
    }


    // 从堆栈中移除一个实例
    protected static void remove_activity(KnowledgeBaseActivity activity) {
        get_activities_stack().remove(activity);
    }

    protected static boolean activities_stack_top_is(Class cls){
        Stack<KnowledgeBaseActivity> activities_stack = get_activities_stack();

        KnowledgeBaseActivity activity = activities_stack.get(activities_stack.size() - 1);
        return activity.getClass() == cls;
    }


    // 先遍历查找相同类型的 activitiy，如果存在，就清除并关闭两个activity之间的所有实例
    // 先查找类型相同的实例的下标
    protected static void tidy_by_class(Class activity_cls){
        Stack<KnowledgeBaseActivity> activities_stack = get_activities_stack();

        int index = -1;
        int size = activities_stack.size();
        for (int i = 0; i < size; i++) {
            KnowledgeBaseActivity activity = activities_stack.get(i);
            if (activity_cls == activity.getClass()) {
                index = i;
                break;
            }
        }

        // 如果找到，清除之
        if (index > -1) {
            int pops_count = size - index;
            for (int i = 0; i < pops_count; i++) {
                KnowledgeBaseActivity item = activities_stack.pop();
                item.finish();
            }
        }
    }


    protected static void tidy_and_push_activity(KnowledgeBaseActivity new_activity) {
        Class<?> cls = new_activity.getClass();

        tidy_by_class(cls);

        Stack<KnowledgeBaseActivity> activities_stack = get_activities_stack();
        activities_stack.push(new_activity);
        // System.out.println("处理后：activities堆栈包含"+ activities_stack.size()
        // +"个实例");
    }
}