package com.mizhousoft.commons.restclient.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizhousoft.commons.restclient.RestException;

/**
 * RestJSONUtils
 *
 * @version
 */
public abstract class RestJSONUtils
{
	// Serialize ObjectMapper
	private static final ObjectMapper SERI_OBJECT_MAPPER = new ObjectMapper();

	// Deserialize ObjectMapper
	private static final ObjectMapper DESERI_OBJECT_MAPPER = new ObjectMapper();

	static
	{
		// 序列化忽略空字符
		SERI_OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);

		// 反序列化忽略未知字段
		DESERI_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param input
	 * @param clazz
	 * @return
	 * @throws RestException
	 */
	public static <T> T parse(String input, Class<T> clazz) throws RestException
	{
		if (StringUtils.isBlank(input))
		{
			throw new RestException("JSON data is null.");
		}

		try
		{
			return DESERI_OBJECT_MAPPER.readValue(input, clazz);
		}
		catch (IOException e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}

	/**
	 * 对象序列化成字符串
	 * 
	 * @param value
	 * @return
	 * @throws RestException
	 */
	public static String toJSONString(Object value) throws RestException
	{
		if (null == value)
		{
			throw new RestException("Serialize object is null.");
		}

		try
		{
			String data = SERI_OBJECT_MAPPER.writeValueAsString(value);
			return data;
		}
		catch (IOException e)
		{
			throw new RestException(e.getMessage(), e);
		}
	}
}
