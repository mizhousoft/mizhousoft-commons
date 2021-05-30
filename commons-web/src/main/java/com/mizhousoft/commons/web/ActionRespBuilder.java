package com.mizhousoft.commons.web;

import java.util.HashMap;
import java.util.Map;

/**
 * ActionResponse构建器
 *
 * @version
 */
public abstract class ActionRespBuilder
{
	public static final String OKEY_FIELD = "okey";

	public static final String ERROR_FIELD = "error";

	public static final String ERROR_CODE_FIELD = "errorCode";

	public static final String DATA_FIELD = "data";

	/**
	 * 构建成功响应
	 * 
	 * @return
	 */
	public static ActionResponse buildSucceedResp()
	{
		ActionResponse response = new ActionResponse();
		response.setOkey(true);
		return response;
	}

	/**
	 * 构建成功响应
	 * 
	 * @param object
	 * @return
	 */
	public static GenericResponse<Object> buildSucceedResp(Object object)
	{
		GenericResponse<Object> response = new GenericResponse<Object>();
		response.setOkey(true);
		if (null != object)
		{
			response.setData(object);
		}

		return response;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @return
	 */
	public static ActionResponse buildFailedResp(String error)
	{
		ActionResponse response = new ActionResponse();
		response.setOkey(false);
		response.setError(error);
		return response;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param errorCode
	 * @return
	 */
	public static ActionResponse buildFailedResp(String error, String errorCode)
	{
		ActionResponse response = new ActionResponse();
		response.setOkey(false);
		response.setError(error);
		response.setErrorCode(errorCode);
		return response;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param object
	 * @return
	 */
	public static GenericResponse<Object> buildFailedResp(String error, Object object)
	{
		GenericResponse<Object> response = new GenericResponse<Object>();
		response.setOkey(false);
		response.setError(error);

		if (null != object)
		{
			response.setData(object);
		}

		return response;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param errorCode
	 * @param object
	 * @return
	 */
	public static GenericResponse<Object> buildFailedResp(String error, String errorCode, Object object)
	{
		GenericResponse<Object> response = new GenericResponse<Object>();
		response.setOkey(false);
		response.setError(error);
		response.setErrorCode(errorCode);

		if (null != object)
		{
			response.setData(object);
		}

		return response;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @return
	 */
	public static Map<String, Object> buildFailedMap(String error)
	{
		Map<String, Object> result = new HashMap<>(2);
		result.put(OKEY_FIELD, false);
		result.put(ERROR_FIELD, error);

		return result;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param errorCode
	 * @return
	 */
	public static Map<String, Object> buildFailedMap(String error, String errorCode)
	{
		Map<String, Object> result = new HashMap<>(3);
		result.put(OKEY_FIELD, false);
		result.put(ERROR_FIELD, error);
		result.put(ERROR_CODE_FIELD, errorCode);

		return result;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param object
	 * @return
	 */
	public static Map<String, Object> buildFailedMap(String error, Object object)
	{
		Map<String, Object> result = new HashMap<>(3);
		result.put(OKEY_FIELD, false);
		result.put(ERROR_FIELD, error);

		if (null != object)
		{
			result.put(DATA_FIELD, object);
		}

		return result;
	}

	/**
	 * 构建失败响应
	 * 
	 * @param error
	 * @param errorCode
	 * @param object
	 * @return
	 */
	public static Map<String, Object> buildFailedMap(String error, String errorCode, Object object)
	{
		Map<String, Object> result = new HashMap<>(3);
		result.put(OKEY_FIELD, false);
		result.put(ERROR_FIELD, error);
		result.put(ERROR_CODE_FIELD, errorCode);

		if (null != object)
		{
			result.put(DATA_FIELD, object);
		}

		return result;
	}
}
