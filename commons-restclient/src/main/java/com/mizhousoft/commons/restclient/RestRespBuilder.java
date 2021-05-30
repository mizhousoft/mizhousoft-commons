package com.mizhousoft.commons.restclient;

/**
 * Rest响应构建器
 *
 * @version
 */
public abstract class RestRespBuilder
{
	/**
	 * 构建成功响应
	 * 
	 * @return
	 */
	public static RestResponse buildSucceedResp()
	{
		return buildSucceedResp(null);
	}

	/**
	 * 构建成功响应
	 * 
	 * @param description
	 * @return
	 */
	public static RestResponse buildSucceedResp(String description)
	{
		return buildResp(RestRespConstants.SUCCEED, null, description);
	}

	/**
	 * 构建失败响应
	 * 
	 * @param errorMsg
	 * @return
	 */
	public static RestResponse buildFailedResp(String errorMsg)
	{
		return buildFailedResp(errorMsg, null);
	}

	/**
	 * 构建失败响应
	 * 
	 * @param errorMsg
	 * @param description
	 * @return
	 */
	public static RestResponse buildFailedResp(String errorMsg, String description)
	{
		return buildResp(RestRespConstants.FAILED, errorMsg, description);
	}

	/**
	 * 构建响应
	 * 
	 * @param statusCode
	 * @param errorMsg
	 * @param description
	 * @return
	 */
	private static RestResponse buildResp(int statusCode, String errorMsg, String description)
	{
		RestResponse response = new RestResponse();
		response.setStatusCode(statusCode);
		response.setErrorMsg(errorMsg);
		response.setDescription(description);
		return response;
	}
}
