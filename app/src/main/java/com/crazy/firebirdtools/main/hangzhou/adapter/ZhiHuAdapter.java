package com.crazy.firebirdtools.main.hangzhou.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.main.shanghai.dto.ShangHaiDetailBean;
import com.crazy.firebirdtools.main.shanghai.dto.ShanghaiBean;
import com.crazy.firebirdtools.main.shanghai.view.ShanhaiDetailActivity;

import java.util.List;

public class ZhiHuAdapter extends RecyclerView.Adapter{


    private static final String TAG = "ZhiHuAdapter";
    private Activity mContext;
    private List<ShangHaiDetailBean.XiaoHuaBean> mData;

    public ZhiHuAdapter(Activity context, List<ShangHaiDetailBean.XiaoHuaBean> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shanghai_fragment, parent, false);
        return new ShanghaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShangHaiDetailBean.XiaoHuaBean xiaoHuaBean = mData.get(position);
        ((ShanghaiViewHolder)holder).mTv.setText(xiaoHuaBean.content);
        ((ShanghaiViewHolder)holder).mIv.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIv;
        public TextView mTv;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIv= itemView.findViewById(R.id.item_shanghai_iv);
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShanhaiDetailActivity.start_5_0(mContext, mIv);
                }
            });*/
        }
    }
}
