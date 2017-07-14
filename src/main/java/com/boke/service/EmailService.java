package com.boke.service;

import com.boke.pojo.MailModel;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.*;

/**
 * Created by wangzy on 2017/7/12.
 */
@Service
public class EmailService {

    private String excelPath = "D://";

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private SimpleMailMessage simpleMailMessage;

    public void emailManage(){

        MailModel mail = new MailModel();
        //主题
        mail.setSubject("清单");
        //附件
        Map<String,String> attachments = new HashMap<String, String>();
        attachments.put("清单.xlsx",excelPath+"清单.xlsx");
        mail.setAttachments(attachments);
        //内容
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>你好！<br />");
        builder.append("    附件是个人清单。<br />");
        builder.append("    其中人信息；<br />");
        builder.append("<img src=\"http://localhost:8080/img/defaultAvatar.jpg\">");
        builder.append("</body></html>");
        String content = builder.toString();

        mail.setContent(content);
        sendEmail(mail);
    }

    public void sendEmail(MailModel mail){
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper;
        try{
            messageHelper = new MimeMessageHelper(message,true,"UTF-8");
            //设置发件人邮箱
            if (mail.getEmailFrom()!=null){
                messageHelper.setFrom(mail.getEmailFrom());
            }else {
                messageHelper.setFrom(simpleMailMessage.getFrom());
            }
            //设置收件人邮箱
            if (mail.getToEmails()!=null) {
                String[] toEmailArray = mail.getToEmails().split(";");
                List<String> toEmailList = new ArrayList<String>();
                if (null == toEmailArray || toEmailArray.length <= 0) {
                    System.out.println("收件人邮箱不得为空！");
                } else {
                    for (String s : toEmailArray) {
                        if (s!=null&&!s.equals("")) {
                            toEmailList.add(s);
                        }
                    }
                    if (null == toEmailList || toEmailList.size() <= 0) {
                        System.out.println("收件人邮箱不得为空！");
                    } else {
                        toEmailArray = new String[toEmailList.size()];
                        for (int i = 0; i < toEmailList.size(); i++) {
                            toEmailArray[i] = toEmailList.get(i);
                        }
                    }
                }
                messageHelper.setTo(toEmailArray);
            } else {
                messageHelper.setTo(simpleMailMessage.getTo());
            }

            // 邮件主题
            if (mail.getSubject()!=null) {
                messageHelper.setSubject(mail.getSubject());
            } else {

                messageHelper.setSubject(simpleMailMessage.getSubject());
            }

            FileDataSource file1 = new FileDataSource(new File("D:\\清单.xlsx"));
            messageHelper.addAttachment("清单.xlsx", file1);//添加到附件


            // true 表示启动HTML格式的邮件
            messageHelper.setText(mail.getContent(), true);
            messageHelper.setSentDate(new Date());
            // 发送邮件
            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
