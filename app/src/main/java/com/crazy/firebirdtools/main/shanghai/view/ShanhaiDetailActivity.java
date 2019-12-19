package com.crazy.firebirdtools.main.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseActivity;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.firebirdtools.main.shanghai.manager.GetXiaoHuaTask;
import com.crazy.firebirdtools.main.shanghai.presenter.IShangHaiDetailContract;
import com.crazy.firebirdtools.main.shanghai.presenter.ShangHaiDetailPresenter;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ViewInject(mainLayoutid = R.layout.activity_shanghai_detail)
public class ShanhaiDetailActivity extends BaseActivity implements IShangHaiDetailContract.Iview {

    public static String mAActivityOptionsCompat = "ShanhaiDetailActivity";

    IShangHaiDetailContract.IPresenter mPresenter = new ShangHaiDetailPresenter(this);

    @BindView(R.id.iv_shanghai_detail)
    ImageView mIvShanghaiDetail;

    @Override
    public void afterBindView() {
        initAnimation();
        initGetNeyData();
    }

    private void initGetNeyData() {
        mPresenter.getNetData(10);

//        GetXiaoHuaTask xiaoHuaTask = new GetXiaoHuaTask();
//        xiaoHuaTask.execute("desc", "1", "1");

        /*OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
        builder.addQueryParameter("sort", "desc");
        builder.addQueryParameter("page", "1");
        builder.addQueryParameter("pagesize", "2");
        builder.addQueryParameter("time", "" + System.currentTimeMillis()/1000);
        builder.addQueryParameter("key", "b4e5f33ecc11aacc499cf800f9078bc9");
        Request request = new Request.Builder()
                .url(builder.build())
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.i("test", "onFailure: " +e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.i("test", "onResponse: " + response.body().toString());
            }
        });*/


    }

    private void initAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(mIvShanghaiDetail, mAActivityOptionsCompat);
            //  延时加载
//            postponeEnterTransition();
            startPostponedEnterTransition();
        }
    }

    /**
     *用于Android5.0系统的界面转场动画：共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, ShanhaiDetailActivity.class);
            Pair pair= new Pair(view, mAActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        }
    }

    @Override
    public void showData(ShangHaiDetailBean data) {

    }
}
