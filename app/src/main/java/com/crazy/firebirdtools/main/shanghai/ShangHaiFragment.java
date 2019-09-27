package com.crazy.firebirdtools.main.shanghai;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseFragment;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.shanghai.dto.ShanghaiDataManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;

@ViewInject(mainLayoutid = R.layout.fragment_shanghai)
public class ShangHaiFragment extends BaseFragment {

    @BindView(R.id.tv_shanghai_welcome)
    TextView mTvShanghaiWelcome;
    @BindView(R.id.shanghai_collapsingtoolbarlayout)
    CollapsingToolbarLayout mShanghaiCollapsingtoolbarlayout;
    @BindView(R.id.shanghai_app_barlayout)
    AppBarLayout mShanghaiAppBarlayout;
    @BindView(R.id.shanghai_recyclerview)
    RecyclerView mRecyclerview;

    @Override
    public void afterBindView() {
        initRecycerView();
        initListener();
    }

    private void initRecycerView() {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            data.add("上海市欢迎您");
        }
        mRecyclerview.setAdapter(new ShanghaiAdapter(getActivity(), ShanghaiDataManager.getData(), false));
    }

    private void initListener() {
        mShanghaiAppBarlayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (-verticalOffset < mShanghaiAppBarlayout.getMeasuredHeight() / 2) {
                    mTvShanghaiWelcome.setVisibility(View.INVISIBLE);
                } else {
                    mTvShanghaiWelcome.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
