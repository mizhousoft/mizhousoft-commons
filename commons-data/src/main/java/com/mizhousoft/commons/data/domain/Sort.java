package com.mizhousoft.commons.data.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 排序选项类
 * 必须提供至少一个属性列表必须不包括空或空字符串排序
 * 
 * @version
 */
public class Sort implements Serializable
{
	private static final long serialVersionUID = -8484744770481449860L;

	// 默认的排序
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;

	// 排序列表
	private List<Order> orders;

	/**
	 * 构造函数
	 * 
	 * @param orders
	 */
	public Sort(List<Order> orders)
	{
		if (null == orders || orders.isEmpty())
		{
			throw new IllegalArgumentException("You have to provide at least one sort order to sort by!");
		}

		this.orders = orders;
	}

	/**
	 * 构造函数
	 * 
	 * @param properties
	 */
	public Sort(String... properties)
	{
		this(DEFAULT_DIRECTION, properties);
	}

	/**
	 * 构造函数
	 * 
	 * @param direction
	 * @param properties
	 */
	public Sort(Direction direction, String... properties)
	{
		if (null == properties || 0 == properties.length)
		{
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.orders = new ArrayList<Order>(properties.length);
		for (String propertyName : properties)
		{
			this.orders.add(new Order(direction, propertyName));
		}
	}

	/**
	 * 获取排序字符串
	 * 
	 * @return
	 */
	public String getSortString()
	{
		if (!this.orders.isEmpty())
		{
			Set<String> orderFields = new HashSet<>(this.orders.size());

			for (Order order : orders)
			{
				String orderField = order.getProperty() + " " + order.getDirection().getValue();
				orderFields.add(orderField);
			}

			return StringUtils.join(orderFields, ",");
		}

		return null;
	}

	/**
	 * 创建Sort
	 * 
	 * @param direction
	 * @param properties
	 * @return
	 */
	public static Sort create(Direction direction, String... properties)
	{
		return new Sort(direction, properties);
	}

	/**
	 * 判断是否相等
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (!(obj instanceof Sort))
		{
			return false;
		}

		Sort that = (Sort) obj;

		boolean ordersEqual = this.orders.equals(that.orders);

		return ordersEqual;
	}

	/**
	 * HashCode
	 * 
	 * @return
	 */
	@Override
	public int hashCode()
	{
		int result = 17;

		result = 31 * result + orders.hashCode();

		return result;
	}

	/**
	 * 排序类
	 * 
	 * @version Sort
	 */
	public static class Order
	{
		// 排序
		private Direction direction;

		// 属性
		private String property;

		/**
		 * 构造函数
		 * 
		 * @param direction
		 * @param property
		 */
		public Order(Direction direction, String property)
		{
			if (property == null || "".equals(property.trim()))
			{
				throw new IllegalArgumentException("Property must not null or empty!");
			}

			this.direction = direction == null ? DEFAULT_DIRECTION : direction;
			this.property = property;
		}

		/**
		 * 构造函数
		 * 
		 * @param property
		 */
		public Order(String property)
		{
			this(DEFAULT_DIRECTION, property);
		}

		/**
		 * 获取 Order
		 * 
		 * @return
		 */
		public Direction getDirection()
		{
			return direction;
		}

		/**
		 * 获取属性名称
		 * 
		 * @return
		 */
		public String getProperty()
		{
			return property;
		}

		/**
		 * HashCode
		 * 
		 * @return
		 */
		@Override
		public int hashCode()
		{
			int result = 17;

			result = 31 * result + direction.hashCode();
			result = 31 * result + property.hashCode();

			return result;
		}

		/**
		 * 判断是否相等
		 * 
		 * @param obj
		 * @return
		 */
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
			{
				return true;
			}

			if (!(obj instanceof Order))
			{
				return false;
			}

			Order that = (Order) obj;

			return this.direction.equals(that.direction) && this.property.equals(that.property);
		}
	}

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
		public static Direction fromString(String value)
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

}
