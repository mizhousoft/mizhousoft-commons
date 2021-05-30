package com.mizhousoft.commons.mail.impl;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.mizhousoft.commons.mail.IMailException;
import com.mizhousoft.commons.mail.MailService;

/**
 * EmailServiceImpl Test
 *
 * @version
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
public class TestMailServiceImpl
{
	private String[] to = {};

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.from}")
	private String from;

	/**
	 * 测试发送文本邮件
	 */
	@Test
	public void sendTextMail()
	{
		try
		{
			MailService mailService = getEmailService();
			mailService.sendTextMail(to, "主题：你好普通邮件", "内容：第一封邮件");
		}
		catch (IMailException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void sendHtmlMail()
	{
		try
		{
			MailService mailService = getEmailService();
			mailService.sendHtmlMail(to, "主题：你好html邮件", "<h1>内容：第一封html邮件</h1>");
		}
		catch (IMailException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void sendMailWithAttachment()
	{
		try
		{
			MailService mailService = getEmailService();
			String filePath = TestMailServiceImpl.class.getClassLoader().getResource("application.properties").getPath();
			File file = new File(filePath);

			mailService.sendMailWithAttachment(to, "主题：你好html邮件", "<h1>内容：第一封html邮件</h1>", file.getAbsolutePath());
		}
		catch (IMailException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	private MailService getEmailService()
	{
		MailServiceImpl mailService = new MailServiceImpl();
		mailService.setMailSender(mailSender);
		mailService.setFrom(from);

		return mailService;
	}
}
