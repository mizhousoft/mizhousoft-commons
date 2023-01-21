package com.mizhousoft.commons.json;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * JSONUtils
 *
 * @version
 */
public abstract class JSONUtils
{
	// Serialize ObjectMapper
	private static final ObjectMapper SERI_OBJECT_MAPPER = new ObjectMapper();

	// Deserialize ObjectMapper
	private static final ObjectMapper DESERI_OBJECT_MAPPER = new ObjectMapper();

	static
	{
		// 序列化忽略空字符
		SERI_OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
		SERI_OBJECT_MAPPER.registerModule(new JavaTimeModule());

		// 反序列化忽略未知字段
		DESERI_OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		DESERI_OBJECT_MAPPER.registerModule(new JavaTimeModule());
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param input
	 * @param clazz
	 * @return
	 * @throws JSONException
	 */
	public static <T> T parse(String input, Class<T> clazz) throws JSONException
	{
		if (StringUtils.isBlank(input))
		{
			throw new JSONException("JSON data is null.");
		}

		try
		{
			T t = DESERI_OBJECT_MAPPER.readValue(input, clazz);
			if (null == t)
			{
				throw new JSONException("String deserialize to Object failed.");
			}

			return t;
		}
		catch (IOException e)
		{
			throw new JSONException("String deserialize to Object failed.", e);
		}
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param input
	 * @param valueTypeRef
	 * @return
	 * @throws JSONException
	 */
	public static <T> T parse(String input, TypeReference<T> valueTypeRef) throws JSONException
	{
		if (StringUtils.isBlank(input))
		{
			throw new JSONException("JSON data is null.");
		}

		try
		{
			T t = DESERI_OBJECT_MAPPER.readValue(input, valueTypeRef);
			if (null == t)
			{
				throw new JSONException("String deserialize to Object failed.");
			}

			return t;
		}
		catch (IOException e)
		{
			throw new JSONException("String deserialize to Object failed.", e);
		}
	}

	/**
	 * 对象序列化成字符串
	 * 
	 * @param value
	 * @return
	 * @throws JSONException
	 */
	public static String toJSONString(Object value) throws JSONException
	{
		if (null == value)
		{
			throw new JSONException("Serialize object is null.");
		}

		try
		{
			String data = SERI_OBJECT_MAPPER.writeValueAsString(value);
			return data;
		}
		catch (IOException e)
		{
			throw new JSONException("Object serialize to a string failed.", e);
		}
	}

	public static String toPrettyJSONString(Object value) throws JSONException
	{
		if (null == value)
		{
			throw new JSONException("Serialize object is null.");
		}

		try
		{
			String data = SERI_OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
			return data;
		}
		catch (IOException e)
		{
			throw new JSONException("Object serialize to a string failed.", e);
		}
	}
}
