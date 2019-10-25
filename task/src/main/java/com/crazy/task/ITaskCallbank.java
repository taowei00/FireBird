package com.crazy.task;

public interface ITaskCallbank<Result> {

    void onComplete(Result o);

    void onException(Throwable throwable);
}
