package com.crazy.firebirdtools.main.hangzhou;

import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.base.BaseFragment;
import com.crazy.firebirdtools.base.ViewInject;
import com.crazy.firebirdtools.main.hangzhou.adapter.HangZhouViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;

@ViewInject(mainLayoutid = R.layout.fragment_hangzhou)
public class HangzhouFragment extends BaseFragment {
    @BindView(R.id.tb_tablayout)
    TabLayout mTbTablelayout;
    @BindView(R.id.vp_viewpager)
    ViewPager mVpViewpager;

    @Override
    public void afterBindView() {
        mTbTablelayout.setupWithViewPager(mVpViewpager);

        mVpViewpager.setAdapter(new HangZhouViewPagerAdapter(getChildFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        /**
         * 隔五秒加一条数据
         */
        /*mVpViewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mVpViewpager.getAdapter().notifyDataSetChanged();
            }
        }, 5000);*/
    }
}
