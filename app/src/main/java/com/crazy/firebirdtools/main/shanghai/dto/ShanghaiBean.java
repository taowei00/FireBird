package com.crazy.firebirdtools.main.shanghai.dto;

import java.util.ArrayList;

public class ShanghaiBean {

    private int mItenType = IShanghaiItemType.VERTICAL;
    private String mDec;
    private boolean isShowImg;
    private ArrayList<ShanghaiBean> data;

    public int getItenType() {
        return mItenType;
    }

    public ShanghaiBean setItenType(int itenType) {
        mItenType = itenType;
        return this;
    }

    public String getDec() {
        return mDec;
    }

    public ShanghaiBean setDec(String dec) {
        mDec = dec;
        return this;
    }

    public boolean isShowImg() {
        return isShowImg;
    }

    public ShanghaiBean setShowImg(boolean showImg) {
        isShowImg = showImg;
        return this;
    }

    public ArrayList<ShanghaiBean> getData() {
        return data;
    }

    public ShanghaiBean setData(ArrayList<ShanghaiBean> data) {
        this.data = data;
        return this;
    }

    public interface IShanghaiItemType {
        int VERTICAL = 0;
        int HORIZANTAL = 1;
    }
}
