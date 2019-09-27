package com.crazy.firebirdtools.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.crazy.firebirdtools.mvp.view.LifeCircleMvpActivity;
import com.crazy.firebirdtools.mvp.view.LifeCircleMvpFragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifeCircleMvpFragment {

    protected Context mContext;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        ViewInject annotation = this.getClass().getAnnotation(ViewInject.class);
        if (annotation != null) {
            int layoutId = annotation.mainLayoutid();
            if (layoutId > 0) {
                Log.i("test", "onCreateView: " + layoutId);
                view = inflater.inflate(layoutId, null);
                bindView(view);
                afterBindView();
            } else {
                throw new RuntimeException("layoutId < 0");
            }
        } else {
            throw new RuntimeException("annotation = null");
        }
        return view;
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
    }*/

    public abstract void afterBindView();

    private void bindView(View view) {
        ButterKnife.bind(this, view);
    }
}
