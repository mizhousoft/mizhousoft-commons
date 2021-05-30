package com.mizhousoft.commons.restclient.util;

import com.mizhousoft.commons.restclient.RestRespConstants;
import com.mizhousoft.commons.restclient.RestResponse;

/**
 * Rest响应工具类
 *
 * @version
 */
public abstract class RestRespUtils
{
	/**
	 * 是否成功
	 * 
	 * @return
	 */
	public static boolean isSucceedResp(RestResponse response)
	{
		return (response.getStatusCode() == RestRespConstants.SUCCEED);
	}

	/**
	 * 是否失败
	 * 
	 * @return
	 */
	public static boolean isFailedResp(RestResponse response)
	{
		return !isSucceedResp(response);
	}
}
