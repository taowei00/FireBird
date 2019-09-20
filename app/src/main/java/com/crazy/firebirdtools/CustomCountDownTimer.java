package com.crazy.firebirdtools;

import android.os.Handler;

/**
 * 计时器
 */
public class CustomCountDownTimer implements Runnable{

    private int time;
    private int countDownTime;
    private ICountDownHandler countDownHandler;
    private Handler mHandler;
    private boolean isRun;

    public CustomCountDownTimer(int time, ICountDownHandler countDownHandler) {
        mHandler = new Handler();
        this.time = time;
        this.countDownHandler = countDownHandler;
    }

    public void start() {
        isRun = true;
        countDownTime = time;
        mHandler.post(this);
    }

    public void cancel() {
        isRun = false;
        mHandler.removeCallbacks(this);
    }

    @Override
    public void run() {
        if (isRun) {
            if (countDownHandler != null) {
                countDownHandler.onTicker(countDownTime);
                if (countDownTime == 0) {
                    cancel();
                    countDownHandler.onFinish();
                } else {
                    countDownTime = time--;
                    mHandler.postDelayed(this, 1000);
                }
            }
        }
    }

    public interface ICountDownHandler {
        void onTicker(int time);
        void onFinish();
    }

}
