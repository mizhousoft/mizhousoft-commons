package com.mizhousoft.commons.restclient;

import java.security.GeneralSecurityException;
import java.security.KeyStore;

/**
 * 证书加载器
 *
 * @version
 */
public interface KeyStoreLoader
{
	/**
	 * 加载证书
	 * 
	 * @return
	 * @throws SecurityException
	 */
	KeyStore loadKeyStore() throws GeneralSecurityException;

	/**
	 * 获取密码
	 * 
	 * @return
	 */
	char[] getKeyPassword();
}
