package com.mizhousoft.commons.json;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;

/**
 * JSONUtils
 *
 * @version
 */
public abstract class JSONUtils
{
	private static final String dateFormat = "yyyy-MM-dd";

	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);

	private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
	        .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
	        .changeDefaultPropertyInclusion(incl -> incl.withContentInclusion(JsonInclude.Include.NON_NULL))
	        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
	        .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
	        .defaultTimeZone(TimeZone.getTimeZone("GMT+8"))
	        .addModule(new SimpleModule().addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter))
	                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter))
	                .addSerializer(new LocalDateSerializer(dateFormatter))
	                .addSerializer(new LocalDateTimeSerializer(dateTimeFormatter)))
	        .build();

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
			T t = OBJECT_MAPPER.readValue(input, clazz);
			if (null == t)
			{
				throw new JSONException("String deserialize to Object failed.");
			}

			return t;
		}
		catch (JacksonException e)
		{
			throw new JSONException("String deserialize to Object failed.", e);
		}
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param <T>
	 * @param input
	 * @param clazz
	 * @return
	 * @throws JSONException
	 */
	public static <T> T parseWithClass(String input, Class<T> clazz) throws JSONException
	{
		return parse(input, clazz);
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param <T>
	 * @param input
	 * @param clazz
	 * @return
	 */
	public static <T> T parseQuietly(String input, Class<T> clazz)
	{
		if (StringUtils.isBlank(input))
		{
			return null;
		}

		try
		{
			T t = OBJECT_MAPPER.readValue(input, clazz);

			return t;
		}
		catch (JacksonException e)
		{
			throw new IllegalArgumentException("String deserialize to Object failed.", e);
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
			T t = OBJECT_MAPPER.readValue(input, valueTypeRef);
			if (null == t)
			{
				throw new JSONException("String deserialize to Object failed.");
			}

			return t;
		}
		catch (JacksonException e)
		{
			throw new JSONException("String deserialize to Object failed.", e);
		}
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param <T>
	 * @param input
	 * @param valueTypeRef
	 * @return
	 * @throws JSONException
	 */
	public static <T> T parseWithTypeRef(String input, TypeReference<T> valueTypeRef) throws JSONException
	{
		return parse(input, valueTypeRef);
	}

	/**
	 * 解析字符串成对象
	 * 
	 * @param <T>
	 * @param input
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T parseQuietly(String input, TypeReference<T> valueTypeRef)
	{
		if (StringUtils.isBlank(input))
		{
			return null;
		}

		try
		{
			T t = OBJECT_MAPPER.readValue(input, valueTypeRef);

			return t;
		}
		catch (JacksonException e)
		{
			throw new IllegalArgumentException("String deserialize to Object failed.", e);
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
			String data = OBJECT_MAPPER.writeValueAsString(value);
			return data;
		}
		catch (JacksonException e)
		{
			throw new JSONException("Object serialize to a string failed.", e);
		}
	}

	/**
	 * 对象序列化成字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String toJSONStringQuietly(Object value)
	{
		if (null == value)
		{
			return null;
		}

		try
		{
			String data = OBJECT_MAPPER.writeValueAsString(value);

			return data;
		}
		catch (JacksonException e)
		{
			throw new IllegalArgumentException("Object serialize to a string failed.", e);
		}
	}

	/**
	 * 对象序列化成格式化的字符串
	 * 
	 * @param value
	 * @return
	 * @throws JSONException
	 */
	public static String toPrettyJSONString(Object value) throws JSONException
	{
		if (null == value)
		{
			throw new JSONException("Serialize object is null.");
		}

		try
		{
			String data = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
			return data;
		}
		catch (JacksonException e)
		{
			throw new JSONException("Object serialize to a string failed.", e);
		}
	}

}
