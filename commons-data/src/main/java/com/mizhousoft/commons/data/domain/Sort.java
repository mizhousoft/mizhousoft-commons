package com.mizhousoft.commons.data.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 排序选项类
 * 必须提供至少一个属性列表必须不包括空或空字符串排序
 * 
 * @version
 */
public class Sort implements Iterable<Sort.Property>, Serializable
{
	private static final long serialVersionUID = -8484744770481449860L;

	// 默认的排序
	public static final Direction DEFAULT_DIRECTION = Direction.ASC;

	// 排序属性
	private List<Property> properties;

	// 排序
	private Direction order;

	/**
	 * 构造函数
	 * 
	 * @param properties
	 */
	public Sort(List<Property> properties)
	{
		if (null == properties || properties.isEmpty())
		{
			throw new IllegalArgumentException("You have to provide at least one sort property to sort by!");
		}

		this.properties = properties;
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
	 * @param order
	 * @param properties
	 */
	public Sort(Direction order, String... properties)
	{
		if (null == properties || 0 == properties.length)
		{
			throw new IllegalArgumentException("You have to provide at least one property to sort by!");
		}

		this.properties = new ArrayList<Property>(properties.length);
		this.order = null == order ? DEFAULT_DIRECTION : order;

		for (String propertyName : properties)
		{
			this.properties.add(new Property(order, propertyName));
		}
	}

	/**
	 * 获取排序属性Iterator
	 * 
	 * @return
	 */
	public Iterator<Property> iterator()
	{
		return this.properties.iterator();
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

		boolean orderEqual = this.order.equals(that.order);
		boolean propertiesEqual = this.properties.equals(that.properties);

		return orderEqual && propertiesEqual;
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

		result = 31 * result + order.hashCode();
		result = 31 * result + properties.hashCode();

		return result;
	}

	/**
	 * 属性类
	 * 
	 * @version Sort
	 */
	public static class Property
	{
		// 排序
		private Direction order;

		// 属性
		private String property;

		/**
		 * 构造函数
		 * 
		 * @param order
		 * @param property
		 */
		public Property(Direction order, String property)
		{
			if (property == null || "".equals(property.trim()))
			{
				throw new IllegalArgumentException("Property must not null or empty!");
			}

			this.order = order == null ? DEFAULT_DIRECTION : order;
			this.property = property;
		}

		/**
		 * 构造函数
		 * 
		 * @param property
		 */
		public Property(String property)
		{
			this(DEFAULT_DIRECTION, property);
		}

		/**
		 * 获取 Order
		 * 
		 * @return
		 */
		public Direction getOrder()
		{
			return order;
		}

		/**
		 * 获取属性名称
		 * 
		 * @return
		 */
		public String getName()
		{
			return property;
		}

		/**
		 * 是否升序
		 * 
		 * @return
		 */
		public boolean isAscending()
		{
			return this.order.equals(Direction.ASC);
		}

		/**
		 * 
		 * @param order
		 * @return
		 */
		public Property with(Direction order)
		{
			return new Property(order, this.property);
		}

		/**
		 * 
		 * 
		 * @param properties
		 * @return
		 */
		public Sort withProperties(String... properties)
		{
			return new Sort(this.order, properties);
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

			result = 31 * result + order.hashCode();
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

			if (!(obj instanceof Property))
			{
				return false;
			}

			Property that = (Property) obj;

			return this.order.equals(that.order) && this.property.equals(that.property);
		}
	}
}
