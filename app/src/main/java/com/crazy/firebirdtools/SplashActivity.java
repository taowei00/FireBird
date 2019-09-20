package com.crazy.firebirdtools;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.crazy.firebirdtools.mvp.ISplashActivityContract;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

@ViewInject(mainLayoutid = R.layout.activity_splash)
public class SplashActivity extends BaseActivity implements ISplashActivityContract.Iview {

    @BindView(R.id.vv_play)
    FullScreenVideoView mVvPlay;
    @BindView(R.id.tv_splash_timer)
    TextView mTvSplashTimer;

    private ISplashActivityContract.IPresenter mTimerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mTimerPresenter.onDestroy();
//    }

}
