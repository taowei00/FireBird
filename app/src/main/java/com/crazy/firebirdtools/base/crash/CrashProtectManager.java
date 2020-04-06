package com.crazy.firebirdtools.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by ${tw}
 * 捕捉未经处理的异常  避免app奔溃   提升用户体验
 * on 2020/1/9
 */
public class CrashProtectManager {

    private static Context sContext;
    private static CrashProtectManager sCrashProtectManager;

    private CrashProtectManager() {
    }

    /**
     * 单例用的是静态  最好使用context.getApplicationContext()来获取context
     * 避免内存泄露
     *
     * @param context
     * @return
     */
    public static CrashProtectManager getInstance(Context context) {
        if (sCrashProtectManager == null) {
            sContext = context.getApplicationContext();
            sCrashProtectManager = new CrashProtectManager();
        }
        return sCrashProtectManager;
    }

    public void init() {
        //  crash防护
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                handlerFileException(e);
                if (t == Looper.getMainLooper().getThread()) {
                    handlerMainThread(e);
                }
            }
        });
    }

    private void handlerMainThread(Throwable e) {
        while (true) {
            try {
                Looper.loop();
            } catch (Throwable throwable) {
                handlerFileException(throwable);
            }
        }
    }

    private void handlerFileException(Throwable throwable) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();

        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_DD_HH_mm_ss");
        String time = dateFormat.format(new Date());
        String fileName = "crash_" + time + ".txt";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                File cacheDir = sContext.getCacheDir();
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                File file = new File(cacheDir.getAbsolutePath(), fileName);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(result.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
