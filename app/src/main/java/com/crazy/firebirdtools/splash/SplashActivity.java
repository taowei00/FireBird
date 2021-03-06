package com.crazy.firebirdtools.splash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.crazy.firebirdtools.base.BaseActivity;
import com.crazy.firebirdtools.main.MainActivity;
import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.ViewInject;

import java.io.File;

import butterknife.BindView;

@ViewInject(mainLayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVvPlay;
    @BindView(R.id.tv_splash_timer)
    TextView mTvSplashTimer;

    private ISplashActivityContract.IPresenter mTimerPresenter;

    /**
     * 通过模板方法设计模式让子类强制实现这个方法
     */
    @Override
    public void afterBindView() {
        initTimerPresent();
        initListener();
        initVideo();
    }

    private void initTimerPresent() {
        mTimerPresenter = new SplashTimerPresenter(this);
        mTimerPresenter.initTimer();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initVideo() {
        mVvPlay.setVideoURI(Uri.parse("android.resource://" + getPackageName() + File.separator + R.raw.splash));
        mVvPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }

    private void initListener() {
        mTvSplashTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });

        mVvPlay.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });

    }

    @Override
    public void setTvTimer(String s) {
        mTvSplashTimer.setText(s);
    }

}
