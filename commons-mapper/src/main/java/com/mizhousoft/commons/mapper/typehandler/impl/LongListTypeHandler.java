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
public class LongListTypeHandler extends ListTypeHandler<Long>
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TypeReference<List<Long>> specificType()
	{
		return new TypeReference<List<Long>>()
		{
		};
	}

}
