package com.example.demo;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import javax.swing.*;
import java.awt.*;


/**
 * 相机测试
 */
public class BroWebCam {

    public void getPic() {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        //实现拍照保存-------start
        String fileName = "D://"+System.currentTimeMillis();       //保存路径即图片名称（不用加后缀）
        WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
        webcam.close();


    }


}