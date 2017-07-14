package com.boke.controller;

import com.boke.service.EmailService;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.logging.Logger;

/**
 * Created by wangzy on 2017/7/12.
 */
@Controller
public class EmailTaskController {

    @Resource
    EmailService emailService;

    @ResponseBody
    @RequestMapping("/sendEmailTask")
    public String sendEmailTask() {
        System.out.println("-------------执行发送邮件START---------------");
        //写入excel
        //insuranceService.excelManage();
        //发邮件
        emailService.emailManage();

      System.out.println("-------------执行发送邮件END---------------");
          return "success";
    }

}
