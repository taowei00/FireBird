package com.crazy.firebirdtools.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.crazy.mvp.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends LifeCircleMvpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int layoutId = annotation.mainLayoutid();
            if (layoutId > 0) {
                setContentView(layoutId);
                bindView();
                afterBindView();
            } else {
                throw new RuntimeException("layoutId < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }

    public abstract void afterBindView();

    private void bindView() {
        ButterKnife.bind(this);
    }
}
