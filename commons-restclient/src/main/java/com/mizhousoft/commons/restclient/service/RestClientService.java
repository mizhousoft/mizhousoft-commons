package com.mizhousoft.commons.restclient.service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import com.mizhousoft.commons.restclient.RestException;

/**
 * Rest客户端调用服务
 *
 * @version
 */
public interface RestClientService
{
	/**
	 * 执行get请求获取数据
	 * 
	 * @param url
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T getForObject(String url, Class<T> responseType) throws RestException;

	/**
	 * 执行get请求获取数据
	 * 
	 * @param <T>
	 * @param url
	 * @param headerMap
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T getForObject(String url, Map<String, String> headerMap, Class<T> responseType) throws RestException;

	/**
	 * 执行POST请求
	 * 
	 * @param url
	 * @param request
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T postForObject(String url, Object request, Class<T> responseType) throws RestException;

	/**
	 * 执行POST请求
	 * 
	 * @param <T>
	 * @param url
	 * @param body
	 * @param headerMap
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T postJSONForObject(String url, String body, Map<String, String> headerMap, Class<T> responseType) throws RestException;

	/**
	 * 执行POST请求
	 * 
	 * @param <T>
	 * @param url
	 * @param body
	 * @param headerMap
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T postSoapForObject(String url, String body, Map<String, String> headerMap, Class<T> responseType) throws RestException;

	/**
	 * 执行POST请求
	 * 
	 * @param <T>
	 * @param url
	 * @param formMap
	 * @param headerMap
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T postFormForObject(String url, Map<String, Object> formMap, Map<String, String> headerMap, Class<T> responseType)
	        throws RestException;

	/**
	 * 下载
	 * 
	 * @param url
	 * @return
	 * @throws RestException
	 */
	InputStream download(String url) throws RestException;

	/**
	 * 下载
	 * 
	 * @param url
	 * @return
	 * @throws RestException
	 */
	InputStream download(String url, Object request) throws RestException;

	/**
	 * 删除
	 * 
	 * @param url
	 * @throws RestException
	 */
	void delete(String url) throws RestException;

	/**
	 * 上传二进制文件
	 * 
	 * @param url
	 * @param file
	 * @param responseType
	 * @return
	 * @throws RestException
	 */
	<T> T uploadBinaryFile(String url, File file, Class<T> responseType) throws RestException;
}
