package com.mizhousoft.commons.mapper.typehandler.impl;

import java.util.Set;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mizhousoft.commons.mapper.typehandler.SetTypeHandler;

/**
 * 类型处理器
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({ Object.class })
public class IntegerSetTypeHandler extends SetTypeHandler<Integer>
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeReference<Set<Integer>> specificType()
	{
		return new TypeReference<Set<Integer>>()
		{
		};
	}

}
