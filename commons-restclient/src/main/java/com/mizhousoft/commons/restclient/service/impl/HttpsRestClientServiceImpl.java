package com.mizhousoft.commons.restclient.service.impl;

import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import com.mizhousoft.commons.restclient.KeyStoreLoader;
import com.mizhousoft.commons.restclient.RestException;
import com.mizhousoft.commons.restclient.TruststoreLoader;

/**
 * Rest客户端调用服务
 *
 * @version
 */
public class HttpsRestClientServiceImpl extends HttpRestClientServiceImpl
{
	private TruststoreLoader[] truststoreLoaders;

	private KeyStoreLoader[] keyStoreLoaders;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CloseableHttpClient createHttpClient() throws RestException
	{
		try
		{
			HttpClientBuilder builder = HttpClientBuilder.create();

			SSLContextBuilder sslBuilder = SSLContexts.custom();

			if (!ArrayUtils.isEmpty(truststoreLoaders))
			{
				for (TruststoreLoader truststoreLoader : truststoreLoaders)
				{
					KeyStore trustStore = truststoreLoader.loadTrustStore();
					sslBuilder.loadTrustMaterial(trustStore, null);
				}
			}

			if (!ArrayUtils.isEmpty(keyStoreLoaders))
			{
				for (KeyStoreLoader keyStoreLoader : keyStoreLoaders)
				{
					sslBuilder.loadKeyMaterial(keyStoreLoader.loadKeyStore(), keyStoreLoader.getKeyPassword());
				}
			}

			SSLContext sslContext = sslBuilder.build();

			HostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();

			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
			        new String[] { "TLSv1", "TLSv1.1", "TLSv1.2", "TLSv1.3" }, null, hostnameVerifier);

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
			        .register("https", sslSocketFactory).register("http", PlainConnectionSocketFactory.getSocketFactory()).build();

			PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			connMgr.setMaxTotal(200);
			connMgr.setDefaultMaxPerRoute(50);
			builder.setConnectionManager(connMgr);

			CloseableHttpClient client = builder.build();
			return client;
		}
		catch (Throwable e)
		{
			throw new RuntimeException("Build httpClient failed.", e);
		}
	}

	/**
	 * 设置truststoreLoaders
	 * 
	 * @param truststoreLoaders
	 */
	public void setTruststoreLoaders(TruststoreLoader[] truststoreLoaders)
	{
		this.truststoreLoaders = truststoreLoaders;
	}

	/**
	 * 设置keyStoreLoaders
	 * 
	 * @param keyStoreLoaders
	 */
	public void setKeyStoreLoaders(KeyStoreLoader[] keyStoreLoaders)
	{
		this.keyStoreLoaders = keyStoreLoaders;
	}
}
