package com.crazy.task;

public class TaskHelper {

    public static void submitTask(ITaskBackground iTaskBackground, ITaskCallbank iTaskCallbank) {

        AsyncTaskInstance instance = AsyncTaskInstance.getInstance(iTaskBackground, iTaskCallbank);
        //构建线程池管理
        TaskScheduler.getInstance().submit(instance);

    }
}
