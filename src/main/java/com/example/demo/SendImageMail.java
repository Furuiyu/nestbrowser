package com.example.demo;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;
import java.util.Properties;

public class SendImageMail {

    static {
        System.setProperty("mail.mime.splitlongparameters", "false");
        System.setProperty("mail.mime.charset", "UTF-8");
    }
    public  void send() throws Exception {
        //创建一个配置文件保存并读取信息
        Properties properties = new Properties();

        //设置qq邮箱服务
        properties.setProperty("mail.host", "smtp.qq.com");
        //设置发送协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //设置用户是否需要验证
        properties.setProperty("mail.smtp.auth", "true");

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);


        //=================================准备工作完毕

        //1、创建以session会话对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("2893709687@qq.com","");
            }
        });

        //可以通过session开启Dubug模式，查看所有的过程
        session.setDebug(false);

        //2、获取连接对象，通过session对象获得ransport，需要捕获或者抛出异常
        Transport transport = session.getTransport();

        //3、连接服务器，抛出异常
        transport.connect("smtp.qq.com", "2893709687@qq.com","");

        //4、创建邮件
        MimeMessage mimeMessage = imageMail(session);
        //5. 发送邮件
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());

        //6 关闭连接
        transport.close();

        File file = new File("D:\\testzzz.png");
        file.delete();
    }

    //邮件内容编写
    public static MimeMessage imageMail(Session session) throws MessagingException {
        //图片：需要通过数据流来实现
        //1、准备图片
        MimeBodyPart emaliBody1 = new MimeBodyPart();
        //2、需要将图片变成数据
        //2.1 获取图片的数据
        FileDataSource fileDataSource = new FileDataSource("D:\\testzzz.png");
        //2.2 需要一个数据流的数据来处理
        DataHandler imgData = new DataHandler(fileDataSource);
        //2.3 把图片数据放到邮件消息内容中
        emaliBody1.setDataHandler(imgData);
        //2.4 给图片设置一个名字
        emaliBody1.setContentID("1.jpg");

        //文本

        //拼接邮件，描述关系
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(emaliBody1);

        //描述是什么类型的邮件
        multipart.setSubType("related");   //这表示是一个带图片的邮件

        MimeMessage mimeMessage = new MimeMessage(session);

        //邮件发送人
        mimeMessage.setFrom(new InternetAddress("2893709687@qq.com"));

        mimeMessage.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("2893709687@qq.com")});
        //邮件标题
        mimeMessage.setSubject("111");

        mimeMessage.setContent(multipart);
        mimeMessage.saveChanges();   //保存修改

        return mimeMessage;


    }
}
