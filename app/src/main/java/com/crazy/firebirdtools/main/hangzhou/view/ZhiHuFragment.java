package com.crazy.firebirdtools.main.hangzhou.view;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseFragment;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.hangzhou.adapter.ZhiHuAdapter;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.firebirdtools.main.shanghai.presenter.IShangHaiDetailContract;
import com.crazy.firebirdtools.main.shanghai.presenter.ShangHaiDetailPresenter;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;

/**
 * created by ${tw}
 * on 2019/12/18
 */
@ViewInject(mainLayoutid = R.layout.activity_zhihu)
public class ZhiHuFragment extends BaseFragment implements IShangHaiDetailContract.Iview {
    IShangHaiDetailContract.IPresenter mPresenter = new ShangHaiDetailPresenter(this);
    @BindView(R.id.zhihu_app_barlayout)
    AppBarLayout mZhihuAppBarlayout;
    @BindView(R.id.zhihu_recyclerview)
    RecyclerView mZhihuRecyclerview;
    @BindView(R.id.zhihu_toolbar)
    Toolbar mZhihuToolbar;

    @Override
    public void afterBindView() {
        mZhihuRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (mZhihuRecyclerview.getAdapter() == null) {
            ZhiHuAdapter zhiHuAdapter = new ZhiHuAdapter(getActivity(), data.result.data);
            mZhihuRecyclerview.setAdapter(zhiHuAdapter);
        }
    }
}
