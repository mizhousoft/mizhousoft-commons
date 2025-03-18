package com.mizhousoft.commons.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mizhousoft.commons.data.domain.PageRequest;

/**
 * 分页 Mapper
 *
 * @version
 */
public interface PageableMapper<T, R, ID extends Serializable> extends CrudMapper<T, ID>
{
	/**
	 * 统计
	 * 
	 * @param request
	 * @return
	 */
	long countTotal(@Param("request") PageRequest request);

	/**
	 * 查询
	 * 
	 * @param rowOffset
	 * @param request
	 * @return
	 */
	List<R> findPageData(@Param("rowOffset") long rowOffset, @Param("request") PageRequest request);
}
