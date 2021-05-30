package com.mizhousoft.commons.restclient;

import java.security.GeneralSecurityException;
import java.security.KeyStore;

/**
 * 信任证书加载器
 *
 * @version
 */
public interface TruststoreLoader
{
	/**
	 * 加载信任证书
	 * 
	 * @return
	 * @throws SecurityException
	 */
	KeyStore loadTrustStore() throws GeneralSecurityException;
}
