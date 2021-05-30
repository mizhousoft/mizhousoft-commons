package com.mizhousoft.commons.restclient.service.impl;

import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.List;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.restclient.KeyStoreLoader;
import com.mizhousoft.commons.restclient.RestException;
import com.mizhousoft.commons.restclient.TruststoreLoader;
import com.mizhousoft.commons.restclient.service.RestClientService;

/**
 * Rest客户端调用服务
 *
 * @version
 */
public class HttpsRestClientServiceImpl implements RestClientService
{
	private static final Logger LOG = LoggerFactory.getLogger(HttpsRestClientServiceImpl.class);

	private TruststoreLoader[] truststoreLoaders;

	private KeyStoreLoader[] keyStoreLoaders;

	private RestTemplate restTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getForObject(String url, Class<T> responseType) throws RestException
	{
		try
		{
			return restTemplate.getForObject(url, responseType);
		}
		catch (Throwable e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T postForObject(String url, Object request, Class<T> responseType) throws RestException
	{
		try
		{
			return restTemplate.postForObject(url, request, responseType);
		}
		catch (Throwable e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream download(String url) throws RestException
	{
		try
		{
			ResponseEntity<Resource> entity = restTemplate.getForEntity(url, Resource.class);
			if (!entity.getStatusCode().equals(HttpStatus.OK))
			{
				throw new RestException("Download failed, status code is " + entity.getStatusCodeValue() + '.');
			}

			InputStream istream = entity.getBody().getInputStream();
			return istream;
		}
		catch (Throwable e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream download(String url, Object request) throws RestException
	{
		try
		{
			ResponseEntity<Resource> entity = restTemplate.postForEntity(url, request, Resource.class);
			if (!entity.getStatusCode().equals(HttpStatus.OK))
			{
				throw new RestException("Download failed, status code is " + entity.getStatusCodeValue() + '.');
			}

			InputStream istream = entity.getBody().getInputStream();
			return istream;
		}
		catch (Throwable e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(String url) throws RestException
	{
		try
		{
			restTemplate.delete(url);
		}
		catch (Throwable e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T uploadBinaryFile(String url, File file, Class<T> responseType) throws RestException
	{
		HttpEntity<FileSystemResource> requestEntity = new HttpEntity<>(new FileSystemResource(file));
		ResponseEntity<T> e = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
		return e.getBody();
	}

	/**
	 * 初始化
	 */
	public void init()
	{
		try
		{
			LOG.info("Start to init restclient service.");

			CloseableHttpClient httpClient = createHttpClient();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			requestFactory.setConnectionRequestTimeout(10000);
			requestFactory.setConnectTimeout(10000);
			requestFactory.setReadTimeout(30000);

			restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(requestFactory);

			List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
			Iterator<HttpMessageConverter<?>> iter = converters.iterator();
			while (iter.hasNext())
			{
				HttpMessageConverter<?> converter = iter.next();
				if (converter instanceof StringHttpMessageConverter)
				{
					iter.remove();
				}
			}

			converters.add(0, new StringHttpMessageConverter(CharEncoding.UTF8));

			LOG.info("Init restclient service successfully.");
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (Throwable e)
		{
			throw new RestException("Init restclient service failed.", e);
		}
	}

	/**
	 * 创建HttpClient
	 * 
	 * @return
	 * @throws RestException
	 */
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
			        new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null, hostnameVerifier);

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
			        .register("https", sslSocketFactory).register("http", PlainConnectionSocketFactory.getSocketFactory()).build();

			PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			connMgr.setMaxTotal(200);
			connMgr.setDefaultMaxPerRoute(50);
			builder.setConnectionManager(connMgr);

			CloseableHttpClient client = builder.build();
			return client;
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (Throwable e)
		{
			throw new RestException("Build httpClient failed.", e);
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
