package com.crazy.firebirdtools;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.crazy.firebirdtools.mvp.view.LifeCircleMvpActivity;

import butterknife.ButterKnife;

public class BaseActivity extends LifeCircleMvpActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int layoutId = annotation.mainLayoutid();
            if (layoutId > 0) {
                setContentView(layoutId);
                ButterKnife.bind(this);
            } else {
                throw new RuntimeException("layoutId < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
    }
}
