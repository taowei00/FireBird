package com.crazy.refresh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * created by ${tw}
 * on 2019/12/24
 */
public class DefaultRefreshManager extends BaseRefershManager {

    private TextView mRefreshState;

    public DefaultRefreshManager(Context context) {
        super(context);
    }

    @Override
    public View getHeadView() {
        View view = mLayoutInflater.inflate(R.layout.ulti_header_layout, null, false);
        mRefreshState = view.findViewById(R.id.header_text);
        return view;
    }

    @Override
    public void downRefresh() {
        mRefreshState.setText("下拉刷新");
    }

    @Override
    public void releaseRefresh() {
        mRefreshState.setText("释放刷新");
    }

    @Override
    public void iddleRefresh() {
        mRefreshState.setText("下拉刷新");
    }

    @Override
    public void refreshing() {
        mRefreshState.setText("正在刷新");
    }

    @Override
    public void refreshOver() {
        mRefreshState.setText("完成刷新");
    }

    @Override
    public void refreshPercent(float percent) {

    }
}
