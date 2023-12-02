package com.mizhousoft.commons.web;

/**
 * 验证器
 *
 */
public interface Validator
{
	/**
	 * 验证
	 * 
	 * @throws AssertionException
	 */
	void validate() throws AssertionException;
}
