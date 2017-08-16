package com.zerone.wgp.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by Administrator on 2017/8/16.
 */

public class AirPlaneActivity extends AppCompatActivity {
    //    private final static String COMMAND_AIRPLANE_ON = "settings put global airplane_mode_on 1 \n " +
//            "am broadcast -a android.intent.action.AIRPLANE_MODE --ez state true\n ";
    private final static String COMMAND_AIRPLANE_ON = "adb shell settings put global airplane_mode_on 1 \n";
    //    private final static String COMMAND_AIRPLANE_OFF = "settings put global airplane_mode_on 0 \n" +
//            " am broadcast -a android.intent.action.AIRPLANE_MODE --ez state false\n ";
    private final static String COMMAND_AIRPLANE_OFF = "adb shell settings put global airplane_mode_on 0\n";
    private final static String COMMAND_SU = "su";
    private static final String TAG = "AirPlaneActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ..." + isAirplaneModeOn());
        Runtime mRuntime = Runtime.getRuntime();

//Process中封装了返回的结果和执行错误的结果
        try {
            Process mProcess = mRuntime.exec("su").("adb version");
            Log.d(TAG, "onCreate: "+mProcess);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            setAirplaneModeOn(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    //判断飞行模式开关
    public boolean isAirplaneModeOn() {
        //4.2以下
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return Settings.System.getInt(getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
        } else //4.2或4.2以上
        {
            return Settings.Global.getInt(getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
        }
    }

    //设置飞行模式
    public void setAirplaneModeOn(boolean isEnable) throws IOException {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            Settings.System.putInt(getContentResolver(),
//                    Settings.System.AIRPLANE_MODE_ON, isEnable ? 1 : 0);
//            Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//            intent.putExtra("state", isEnable);
//            sendBroadcast(intent);
//        } else //4.2或4.2以上
//        {

        if (isEnable) {
//                Process proc=Runtime.getRuntime().exec("adb shell settings put global airplane_mode_on 1");
            writeCmd(COMMAND_AIRPLANE_ON);
            Log.d(TAG, "setAirplaneModeOn: ....successsss");
        } else
            writeCmd(COMMAND_AIRPLANE_OFF);
//        }

    }

    //写入shell命令
    public static void writeCmd(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process proc = runtime.exec(command);
        try {
            if (proc.waitFor() != 0) {
                System.err.println("exit value = " + proc.exitValue());
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line + "-");
            }
            System.out.println(stringBuffer.toString());

        } catch (InterruptedException e) {
            System.err.println(e);
        }
//        Runtime runtime = Runtime.getRuntime();
//        Process proc = runtime.exec(command);        //这句话就是shell与高级语言间的调用
//        //如果有参数的话可以用另外一个被重载的exec方法
//        //实际上这样执行时启动了一个子进程,它没有父进程的控制台
//        //也就看不到输出,所以我们需要用输出流来得到shell执行后的输出
//        InputStream inputstream = proc.getInputStream();
//        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
//        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
//        // read the ls output
//        String line = "";
//        StringBuilder sb = new StringBuilder(line);
//        while ((line = bufferedreader.readLine()) != null) {
//            //System.out.println(line);
//            sb.append(line);
//            sb.append('\n');
//        }
//        //tv.setText(sb.toString());
//        //使用exec执行不会等执行成功以后才返回,它会立即返回
//        //所以在某些情况下是很要命的(比如复制文件的时候)
//        //使用wairFor()可以等待命令执行完成以后才返回
//        try {
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//        } catch (InterruptedException e) {
//            System.err.println(e);
//        }
//        try {
//            Process su = Runtime.getRuntime().exec(COMMAND_SU);
//            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
//
//            outputStream.writeBytes(command);
//            outputStream.flush();
//
//            outputStream.writeBytes("exit\n");
//            outputStream.flush();
//            try {
//                su.waitFor();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
//    public int exeCmd(String shell) throws IOException {
//        int success = 0;
//        StringBuffer sb = new StringBuffer();
//        BufferedReader br = null;
//
//        // get name representing the running Java virtual machine.
//        String name = ManagementFactory.getRuntimeMXBean().getName();
//        String pid = name.split("@")[0];
//
//        try {
//            System.out.println("Starting to exec{ " + shell + " }. PID is: " + pid);
//            Process process = null;
//            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", shell);
//            pb.environment();
//            pb.redirectErrorStream(true); // merge error stream into standard stream
//            process = pb.start();
//            if (process != null) {
//                br = new BufferedReader(
//                        new InputStreamReader(process.getInputStream()), 1024);
//                process.waitFor();
//            } else {
//                System.out.println("There is no PID found.");
//            }
//            sb.append("Ending exec right now, the result is：\n");
//            String line = null;
//            while (br != null && (line = br.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//        } catch (Exception ioe) {
//            sb.append("Error occured when exec cmd：\n").append(ioe.getMessage())
//                    .append("\n");
//        } finally {
//            PrintWriter writer = null;
//            if (br != null) {
//                br.close();
//            }
//            try {
//                writer = new PrintWriter(System.out);
//                writer.write(sb.toString());
//            } catch (Exception e) {
//                Log.d(TAG,e.getMessage());
//            } finally {
//                writer.close();
//            }
//            success = 1;
//        }
//        return success;
//    }
}
