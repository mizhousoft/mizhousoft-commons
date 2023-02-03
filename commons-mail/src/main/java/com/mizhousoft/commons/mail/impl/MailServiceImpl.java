package com.mizhousoft.commons.mail.impl;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.mizhousoft.commons.mail.IMailException;
import com.mizhousoft.commons.mail.MailService;

/**
 * 邮件服务
 *
 * @version
 */
public class MailServiceImpl implements MailService
{
	private static final Logger LOG = LoggerFactory.getLogger(MailServiceImpl.class);

	private JavaMailSender mailSender;

	private String from;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendTextMail(String[] to, String subject, String content) throws IMailException
	{
		if (ArrayUtils.isEmpty(to))
		{
			LOG.warn("To is empty.");
			return;
		}

		try
		{
			// 创建SimpleMailMessage对象
			SimpleMailMessage message = new SimpleMailMessage();
			// 邮件发送人
			message.setFrom(from);
			// 邮件接收人
			message.setTo(to);
			// 邮件主题
			message.setSubject(subject);
			// 邮件内容
			message.setText(content);
			// 发送邮件
			mailSender.send(message);
		}
		catch (MailException e)
		{
			throw new IMailException("Send mail failed.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendHtmlMail(String[] to, String subject, String content) throws IMailException
	{
		if (ArrayUtils.isEmpty(to))
		{
			LOG.warn("To is empty.");
			return;
		}

		// 获取MimeMessage对象
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper;

		try
		{
			messageHelper = new MimeMessageHelper(message, true);
			// 邮件发送人
			messageHelper.setFrom(from);
			// 邮件接收人
			messageHelper.setTo(to);
			// 邮件主题
			message.setSubject(subject);
			// 邮件内容，html格式
			messageHelper.setText(content, true);
			// 发送
			mailSender.send(message);
		}
		catch (MessagingException | MailException e)
		{
			throw new IMailException("Send mail failed.", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendMailWithAttachment(String[] to, String subject, String content, String filePath) throws IMailException
	{
		if (ArrayUtils.isEmpty(to))
		{
			LOG.warn("To is empty.");
			return;
		}

		MimeMessage message = mailSender.createMimeMessage();

		try
		{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
		}
		catch (MessagingException | MailException e)
		{
			throw new IMailException("Send mail failed.", e);
		}
	}

	/**
	 * 设置mailSender
	 * 
	 * @param mailSender
	 */
	public void setMailSender(JavaMailSender mailSender)
	{
		this.mailSender = mailSender;
	}

	/**
	 * 设置from
	 * 
	 * @param from
	 */
	public void setFrom(String from)
	{
		this.from = from;
	}
}
