package com.crazy.firebirdtools.main.shanghai.manager;

import android.os.AsyncTask;
import android.util.Log;

import com.crazy.firebirdtools.main.shanghai.module.ShanghaiDetailHttpTask;

import java.io.IOException;

import okhttp3.Response;

public class GetXiaoHuaTask extends AsyncTask<Object, Object, Object> {
    private String TAG = "GetXiaoHuaTask";

    @Override
    protected Object doInBackground(Object... objects) {
        Object desc = new ShanghaiDetailHttpTask().getXiaoHuaList((String) objects[0], (String) objects[1], (String) objects[2]);
        return desc;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.i(TAG, "onPostExecute: " + o);
        Response response = (Response) o;
        try {
            Log.i(TAG, "onPostExecute: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
