package com.mizhousoft.commons.restclient.service.impl;

import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.DefaultHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.URIScheme;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.ssl.SSLContexts;

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
	public CloseableHttpClient createHttpClient(int readTimeout) throws RestException
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
			        .register(URIScheme.HTTPS.id, sslSocketFactory)
			        .register(URIScheme.HTTP.id, PlainConnectionSocketFactory.getSocketFactory()).build();

			SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(readTimeout, TimeUnit.MILLISECONDS).build();

			PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			connMgr.setDefaultSocketConfig(socketConfig);
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
