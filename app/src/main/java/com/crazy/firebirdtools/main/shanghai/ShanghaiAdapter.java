package com.crazy.firebirdtools.main.shanghai;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crazy.firebirdtools.R;
import com.crazy.firebirdtools.main.shanghai.dto.ShanghaiBean;
import com.crazy.firebirdtools.main.shanghai.view.ShanhaiDetailActivity;

import java.util.List;
import java.util.TreeMap;

public class ShanghaiAdapter extends RecyclerView.Adapter{


    private static final String TAG = "ShanghaiAdapter";
    private boolean isHor;
    private Activity mContext;
    private List<ShanghaiBean> mData;
    private RecyclerView.RecycledViewPool mRecycledViewPool;

    public ShanghaiAdapter(Activity context, List<ShanghaiBean> data, boolean isHor) {
        mData = data;
        mContext = context;
        this.isHor = isHor;
        mRecycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ShanghaiBean.IShanghaiItemType.VERTICAL) {
            if (isHor) {
//                Log.i(TAG, "onCreateViewHolder: " + "VERTICAL");
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shanghai_fragment, parent, false);
            return new ShanghaiViewHolder(view);
        } else if (viewType == ShanghaiBean.IShanghaiItemType.HORIZANTAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_shanghai_fragment_rv, null);
            return new ShanghaiViewHolderRv(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShanghaiBean shanghaiBean = mData.get(position);
        if (holder instanceof ShanghaiViewHolder) {
            ((ShanghaiViewHolder)holder).mTv.setText(shanghaiBean.getDec());
            ((ShanghaiViewHolder)holder).mIv.setVisibility(shanghaiBean.isShowImg()?View.VISIBLE:View.GONE);
        }else if (holder instanceof ShanghaiViewHolderRv) {
            ((ShanghaiViewHolderRv)holder).mRecyclerView.setAdapter(new ShanghaiAdapter(mContext, shanghaiBean.getData(), true));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getItenType();
    }

    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIv;
        public TextView mTv;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
            mIv= itemView.findViewById(R.id.item_shanghai_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShanhaiDetailActivity.start_5_0(mContext, mIv);
                }
            });
        }
    }
    public class ShanghaiViewHolderRv extends RecyclerView.ViewHolder {

        public RecyclerView mRecyclerView;

        public ShanghaiViewHolderRv(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.item_shanghai_rv);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setRecycledViewPool(mRecycledViewPool);
        }
    }

}
