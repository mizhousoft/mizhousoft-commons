package com.mizhousoft.commons.restclient.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.mizhousoft.commons.lang.CharEncoding;
import com.mizhousoft.commons.restclient.RestException;
import com.mizhousoft.commons.restclient.RestResponse;
import com.mizhousoft.commons.restclient.service.RestClientService;

/**
 * Rest客户端调用服务
 *
 * @version
 */
public class HttpRestClientServiceImpl implements RestClientService
{
	private static final Logger LOG = LoggerFactory.getLogger(HttpRestClientServiceImpl.class);

	protected RestTemplate restTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestException
	{
		try
		{
			return restTemplate.getForObject(url, responseType, uriVariables);
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public <T> T getForObject(String url, Map<String, String> headerMap, Class<T> responseType, Object... uriVariables) throws RestException
	{
		return getForObject(url, headerMap, responseType, Set.of(HttpStatus.OK.value()), uriVariables);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T getForObject(String url, Map<String, String> headerMap, Class<T> responseType, Set<Integer> expectStatusCodes,
	        Object... uriVariables) throws RestException
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();
			if (null != headerMap)
			{
				Iterator<Entry<String, String>> iter = headerMap.entrySet().iterator();
				while (iter.hasNext())
				{
					Entry<String, String> entry = iter.next();
					headers.set(entry.getKey(), entry.getValue());
				}
			}

			HttpEntity<?> request = new HttpEntity<>(headers);
			ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, request, responseType, uriVariables);

			int statusCode = response.getStatusCodeValue();
			if (null == expectStatusCodes || !expectStatusCodes.contains(statusCode))
			{
				String body = response.getBody().toString();
				String message = "Http status code is " + response.getStatusCode() + ", body is " + body;
				throw new RestException(response.getStatusCodeValue(), body, message, null);
			}

			return response.getBody();
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public RestResponse get(String url, Map<String, String> headerMap, Object... uriVariables) throws RestException
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();
			if (null != headerMap)
			{
				Iterator<Entry<String, String>> iter = headerMap.entrySet().iterator();
				while (iter.hasNext())
				{
					Entry<String, String> entry = iter.next();
					headers.set(entry.getKey(), entry.getValue());
				}
			}

			HttpEntity<?> request = new HttpEntity<>(headers);
			ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class, uriVariables);

			String respBody = respEntity.getBody();
			HttpHeaders respHeaders = respEntity.getHeaders();
			int statusCode = respEntity.getStatusCode().value();

			RestResponse restResponse = new RestResponse();
			restResponse.setBody(respBody);
			restResponse.setStatusCode(statusCode);
			restResponse.setHeaders(respHeaders.toSingleValueMap());

			return restResponse;
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws RestException
	{
		try
		{
			return restTemplate.postForObject(url, request, responseType, uriVariables);
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public <T> T postJSONForObject(String url, String body, Map<String, String> headerMap, Class<T> responseType, Object... uriVariables)
	        throws RestException
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			if (!MapUtils.isEmpty(headerMap))
			{
				headerMap.forEach((key, value) -> headers.add(key, value));
			}

			HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);

			return restTemplate.postForObject(url, httpEntity, responseType, uriVariables);
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public <T> T postSoapForObject(String url, String body, Map<String, String> headerMap, Class<T> responseType, Object... uriVariables)
	        throws RestException
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/soap+xml; charset=utf-8");
			headers.setContentType(type);
			if (!MapUtils.isEmpty(headerMap))
			{
				headerMap.forEach((key, value) -> headers.add(key, value));
			}

			HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);

			return restTemplate.postForObject(url, httpEntity, responseType, uriVariables);
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public <T> T postFormForObject(String url, Map<String, Object> formMap, Map<String, String> headerMap, Class<T> responseType,
	        Object... uriVariables) throws RestException
	{
		try
		{
			MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
			if (!MapUtils.isEmpty(formMap))
			{
				formMap.forEach((key, value) -> postParameters.add(key, value));
			}

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			if (!MapUtils.isEmpty(headerMap))
			{
				headerMap.forEach((key, value) -> headers.add(key, value));
			}

			HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(postParameters, headers);

			return restTemplate.postForObject(url, entity, responseType, uriVariables);
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
	public RestResponse postJSON(String url, String body, Map<String, String> headerMap, Object... uriVariables) throws RestException
	{
		try
		{
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			if (!MapUtils.isEmpty(headerMap))
			{
				headerMap.forEach((key, value) -> headers.add(key, value));
			}

			HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);

			ResponseEntity<String> respEntity = restTemplate.postForEntity(url, httpEntity, String.class, uriVariables);

			String respBody = respEntity.getBody();
			HttpHeaders respHeaders = respEntity.getHeaders();
			int statusCode = respEntity.getStatusCode().value();

			RestResponse restResponse = new RestResponse();
			restResponse.setBody(respBody);
			restResponse.setStatusCode(statusCode);
			restResponse.setHeaders(respHeaders.toSingleValueMap());

			return restResponse;
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
				throw new RestException(entity.getStatusCodeValue(), null,
				        "Download failed, status code is " + entity.getStatusCodeValue() + '.', null);
			}

			InputStream istream = entity.getBody().getInputStream();
			return istream;
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
				throw new RestException(entity.getStatusCodeValue(), null,
				        "Download failed, status code is " + entity.getStatusCodeValue() + '.', null);
			}

			InputStream istream = entity.getBody().getInputStream();
			return istream;
		}
		catch (RestException e)
		{
			throw e;
		}
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
		catch (RestClientResponseException e)
		{
			throw new RestException(e.getRawStatusCode(), e.getResponseBodyAsString(), e.getMessage(), e);
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
		this.init(10000, 30000);
	}

	/**
	 * 初始化
	 */
	public void init(int connectTimeout, int readTimeout)
	{
		try
		{
			LOG.info("Start to init restclient service.");

			CloseableHttpClient httpClient = createHttpClient();

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(httpClient);
			requestFactory.setConnectionRequestTimeout(10000);
			requestFactory.setConnectTimeout(connectTimeout);
			requestFactory.setReadTimeout(readTimeout);

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
			throw new RuntimeException("Init restclient service failed.", e);
		}
	}

	/**
	 * 创建HttpClient
	 * 
	 * @return
	 */
	public CloseableHttpClient createHttpClient()
	{
		try
		{
			HttpClientBuilder builder = HttpClientBuilder.create();

			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
			        .register("http", PlainConnectionSocketFactory.getSocketFactory()).build();

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
}
