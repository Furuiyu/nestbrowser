package com.example.demo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CaptureScreen {

    //截图 并保存
    public static void captureScreen(String folder,String fileName)throws Exception{
        //获得屏幕大小并创建一个Rectangle(区域)


        GraphicsDevice graphDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode disMode = graphDevice.getDisplayMode();

        Dimension screenSize = new Dimension(disMode.getWidth(),disMode.getHeight());


        Rectangle screenRectangle = new Rectangle(screenSize);
        //创建包含从屏幕中读取的像素的图像
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //保存路径
        File screenFile = new File(folder);
        if(!screenFile.exists()) {
            boolean b = screenFile.mkdir();
            if(b){
                System.out.println("ok");
            }
        }
        File f = new File(screenFile,fileName);
        //决定了f为文件，将图像1以.png格式写入文件f
        boolean b = ImageIO.write(image, "png", f);


    }

}

