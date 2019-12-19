package com.crazy.firebirdtools.main.shanghai.presenter;

import android.util.Log;

import com.crazy.firebirdtools.base.BasePresenter;
import com.crazy.firebirdtools.base.JHTask;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.firebirdtools.main.shanghai.manager.GetXiaoHuaTask;
import com.crazy.firebirdtools.main.shanghai.module.ShanghaiDetailHttpTask;
import com.crazy.http.LfHttpServer;
import com.crazy.http.result.IResult;
import com.crazy.mvp.mvp.base.BaseMvpPresenter;
import com.crazy.task.LfTask;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Response;

public class ShangHaiDetailPresenter extends BasePresenter<IShangHaiDetailContract.Iview> implements IShangHaiDetailContract.IPresenter {

    private static final String TAG = "ShangHaiDetailPresenter";

    public ShangHaiDetailPresenter(IShangHaiDetailContract.Iview view) {
        super(view);
    }

    @Override
    protected IShangHaiDetailContract.Iview getEmptyView() {
        return IShangHaiDetailContract.emptyView;
    }

    @Override
    public void getNetData(int pageSize) {

        //1  数据结果的解析怎么来做
        //2  相同任务的去重工作

        /*submitTask(new LfTask() {
            //  必须回调到主线程
            @Override
            public void onSuccess(Object o) {
                //  获取网络结果
                Log.i(TAG, "onSuccess: " + o.toString());
            }

            @Override
            public void onException(Throwable throwable) {
                Log.i(TAG, "onException: " + throwable.toString());
            }

            @Override
            public Object onBankground() {
                Log.i(TAG, "onBankground: " + Thread.currentThread().getName());
                *//*Response response = (Response) new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
                String strResponse = null;
                try {
                    strResponse = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }*//*
                IResult desc = new ShanghaiDetailHttpTask().getXiaoHuaList("desc", "1", "1");
                return null;
            }
        });*/

        /**
         * 架构师必备条件
         *    1、合理利用继承关系
         *    2、合理利用抽象编程
         *    3、合理利用泛型传递数据
         *    4、合理利用设计模式
         */
        submitTask(new JHTask<ShangHaiDetailBean>() {
            @Override
            public IResult<ShangHaiDetailBean> onBankground() {
                return new ShanghaiDetailHttpTask<ShangHaiDetailBean>().getXiaoHuaList("desc", "1", pageSize+"");
            }

            @Override
            public void onSuccess(IResult<ShangHaiDetailBean> t) {
                ShangHaiDetailBean data = t.data();
//                String s = new Gson().toJson(data);
//                Log.i(TAG, "onSuccess: " + s);
                getView().showData(data);
            }
        });
    }
}
