package com.crazy.firebirdtools.main.hangzhou.view;

import android.view.animation.AnimationUtils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseFragment;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.hangzhou.adapter.ZhiHuAdapter;
import com.crazy.firebirdtools.main.hangzhou.refresh.MeiTuanRefreshManager;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.firebirdtools.main.shanghai.presenter.IShangHaiDetailContract;
import com.crazy.firebirdtools.main.shanghai.presenter.ShangHaiDetailPresenter;
import com.crazy.refresh.GodRefreshLayout;

import butterknife.BindView;

/**
 * created by ${tw}
 * on 2019/12/24
 */
@ViewInject(mainLayoutid = R.layout.fragment_refresh)
public class RefreshFragment extends BaseFragment implements IShangHaiDetailContract.Iview{

    IShangHaiDetailContract.IPresenter mPresenter = new ShangHaiDetailPresenter(this);
    @BindView(R.id.god_refresh)
    GodRefreshLayout mGodRefresh;
    @BindView(R.id.refresh_recyclerview)
    RecyclerView mRefreshRecyclerview;

    @Override
    public void afterBindView() {
        //  默认的下拉刷新view
//        mGodRefresh.setRefreshManager();
        //  自定义的下拉刷新view
        mGodRefresh.setRefreshManager(new MeiTuanRefreshManager(mContext));
        mGodRefresh.setRefreshListener(new GodRefreshLayout.RefreshListener() {
            @Override
            public void refreshing() {
                mPresenter.getNetData(20);
            }
        });
        initRecylerView();
    }

    private void initRecylerView() {
        mRefreshRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getNetData(20);
    }

    @Override
    public void showData(ShangHaiDetailBean data) {
        if (mRefreshRecyclerview.getAdapter() == null) {
            ZhiHuAdapter zhiHuAdapter = new ZhiHuAdapter(getActivity(), data.result.data);
            mRefreshRecyclerview.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.zhihu_recyclerview_show));
            mRefreshRecyclerview.setAdapter(zhiHuAdapter);
        }
        if (mGodRefresh != null) {
            mGodRefresh.refreshOver();
        }
    }
}
