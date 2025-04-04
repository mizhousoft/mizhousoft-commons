package com.mizhousoft.commons.mapper.typehandler.impl;

import java.util.List;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mizhousoft.commons.mapper.typehandler.ListTypeHandler;

/**
 * 类型处理器
 *
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({ Object.class })
public class IntegerListTypeHandler extends ListTypeHandler<Integer>
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeReference<List<Integer>> specificType()
	{
		return new TypeReference<List<Integer>>()
		{
		};
	}

}
