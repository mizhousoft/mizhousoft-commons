package com.mizhousoft.commons.mapper.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mizhousoft.commons.json.JSONException;
import com.mizhousoft.commons.json.JSONUtils;

public abstract class ClassTypeHandler<T> extends BaseTypeHandler<T>
{
	private static final Logger LOG = LoggerFactory.getLogger(ClassTypeHandler.class);

	private Class<T> type;

	public ClassTypeHandler(Class<T> type)
	{
		if (type == null)
		{
			throw new IllegalArgumentException("Type argument cannot be null.");
		}

		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException
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
	public T getNullableResult(ResultSet rs, String columnName) throws SQLException
	{
		String value = rs.getString(columnName);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithClass(value, type);
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}

	@Override
	public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException
	{
		String value = rs.getString(columnIndex);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithClass(value, type);
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}

	@Override
	public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
	{
		String value = cs.getString(columnIndex);
		if (value != null)
		{
			try
			{
				return JSONUtils.parseWithClass(value, type);
			}
			catch (JSONException e)
			{
				LOG.error("String deserialize to Object failed.", e);
			}
		}

		return null;
	}
}