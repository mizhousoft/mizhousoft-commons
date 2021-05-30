package com.mizhousoft.commons.mail;

/**
 * 邮件服务
 *
 * @version
 */
public interface MailService
{
	/**
	 * 发送文本邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @throws IMailException
	 */
	void sendTextMail(String[] to, String subject, String content) throws IMailException;

	/**
	 * 发送HTML邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @throws IMailException
	 */
	void sendHtmlMail(String[] to, String subject, String content) throws IMailException;

	/**
	 * 发送带附件的邮件
	 * 
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @throws IMailException
	 */
	void sendMailWithAttachment(String[] to, String subject, String content, String filePath) throws IMailException;
}