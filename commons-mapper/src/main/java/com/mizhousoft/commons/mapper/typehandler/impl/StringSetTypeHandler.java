package com.mizhousoft.commons.mapper.typehandler.impl;

import java.util.Set;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.mizhousoft.commons.mapper.typehandler.SetTypeHandler;

import tools.jackson.core.type.TypeReference;

/**
 * 类型处理器
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({ Object.class })
public class StringSetTypeHandler extends SetTypeHandler<String>
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeReference<Set<String>> specificType()
	{
		return new TypeReference<Set<String>>()
		{
		};
	}

}
