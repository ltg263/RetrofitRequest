package com.ltg263.happy_dzh.utils;

import android.text.TextUtils;
import android.util.Log;

import com.ltg263.happy_dzh.baseManager.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：log打印控制类
 */
public class LogUtil {
    /**
     * tag前缀,方便过滤
     */
    public static String customTagPrefix = "";

    /**
     * log打印开关
     */
    public static final boolean DEBUG = Constants.DEBUG;

    public static void v(String msg) {
        v(msg, null);
    }

    public static void v(String msg, Throwable tr) {
        if (DEBUG) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.v(tag, msg, tr);
        }
    }

    public static void d(String msg) {
        d(msg, null);
    }

    public static void d(String msg, Throwable tr) {
        if (DEBUG) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.d(tag, msg, tr);
        }
    }

    public static void i(String msg) {
        i(msg, null);
    }

    public static void i(String msg, Throwable tr) {
        if (DEBUG) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.i(tag, msg, tr);
        }
    }

    public static void w(String msg) {
        w(msg, null);
    }

    public static void w(Throwable tr) {
        w(tr.getMessage(), tr);
    }

    public static void w(String msg, Throwable tr) {
        if (DEBUG) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.w(tag, msg, tr);
        }
    }

    public static void e(String msg) {
        e(msg, null);
    }

    public static void e(Throwable tr) {
        e(tr.getMessage(), tr);
    }

    public static void e(String msg, Throwable tr) {
        if (DEBUG) {
            StackTraceElement caller = getCallerStackTraceElement();
            String tag = generateTag(caller);
            Log.e(tag, msg, tr);
        }
    }

    /**
     *
     * @param logFilePath 保存日志的路径
     * @param tr
     * @param override 是否覆盖文件
     */
    public static void f(String logFilePath, Throwable tr, boolean override) {
        File logFile = new File(logFilePath);
        if (!logFile.exists()) {
            File parentFile = logFile.getParentFile();
            if (!parentFile.exists() || !parentFile.isDirectory()) {
                parentFile.mkdirs();
            }
        }
        while (true) {
            if (logFile.canWrite()) {
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(logFile, !override));
                    String msg = Log.getStackTraceString(tr);
                    if (!override) {
                        writer.append(msg);
                    } else {
                        writer.write(msg);
                    }
                    writer.newLine();
                } catch (IOException e) {
                    e(e);
                } finally {
                    try {
                        if (writer != null) {
                            writer.flush();
                            writer.close();
                        }
                    } catch (Exception e2) {
                        e(e2);
                    } finally {
                        writer = null;
                        System.gc();
                    }
                }
                break;
            }
        }
    }
    /**
     * 获取调用者堆栈信息
     *
     * @return
     */
    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[5];
    }
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }
}
