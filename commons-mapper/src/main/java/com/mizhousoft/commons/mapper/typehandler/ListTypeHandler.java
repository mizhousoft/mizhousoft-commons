package com.mizhousoft.commons.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mizhousoft.commons.json.JSONException;
import com.mizhousoft.commons.json.JSONUtils;

/**
 * ListTypeHandler
 *
 */
public abstract class ListTypeHandler<T> extends BaseTypeHandler<List<T>>
{
	private static final Logger LOG = LoggerFactory.getLogger(ListTypeHandler.class);

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<T> parameter, JdbcType jdbcType) throws SQLException
	{
		String value = null;
		if (null != parameter)
		{
			try
			{
				value = JSONUtils.toJSONString(parameter);
			}
			catch (JSONException e)
			{
				LOG.error("Object serialize to a string failed.", e);
			}
		}

		ps.setString(i, value);
	}

	@Override
	public List<T> getNullableResult(ResultSet rs, String columnName) throws SQLException
	{
		String value = rs.getString(columnName);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithTypeRef(value, specificType());
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}

	@Override
	public List<T> getNullableResult(ResultSet rs, int columnIndex) throws SQLException
	{
		String value = rs.getString(columnIndex);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithTypeRef(value, specificType());
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}

	@Override
	public List<T> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
	{
		String value = cs.getString(columnIndex);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithTypeRef(value, specificType());
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}

	protected abstract TypeReference<List<T>> specificType();
}
