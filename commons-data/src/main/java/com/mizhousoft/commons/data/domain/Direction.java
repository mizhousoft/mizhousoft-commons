package com.mizhousoft.commons.data.domain;

/**
 * 排序枚举类
 * 
 * @version
 */
public enum Direction
{
    // 升序
	ASC("asc"),
    // 降序
	DESC("desc");

	/**
	 * 排序值
	 */
	private String value;

	/**
	 * 构造函数
	 * 
	 * @param value
	 */
	private Direction(String value)
	{
		this.value = value;
	}

	/**
	 * 获取排序值
	 * 
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 根据参数返回一个枚举对象
	 * 
	 * @param value
	 * @return
	 */
	public static Direction getDirectionByValue(String value)
	{
		for (Direction direction : Direction.values())
		{
			if (direction.getValue().equalsIgnoreCase(value))
			{
				return direction;
			}
		}

		throw new IllegalArgumentException(
		        String.format("Invalid value '%s' for orders given! Has to be either 'desc' or 'asc'.", value));
	}
}
