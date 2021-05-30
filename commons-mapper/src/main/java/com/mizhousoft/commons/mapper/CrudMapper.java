package com.mizhousoft.commons.mapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 增加读取更新删除Mapper
 *
 * @version
 */
public interface CrudMapper<T, ID extends Serializable> extends BaseMapper<T, ID>
{
	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> void save(S entity);

	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @return
	 */
	<S extends T> void update(S entity);

	/**
	 * 根据ID删除对象
	 * 
	 * @param id
	 */
	void delete(ID id);

	/**
	 * 根据ID查找
	 * 
	 * @param id
	 * @return
	 */
	T findById(@Param("id") ID id);

	/**
	 * 根据id查找对象
	 * 
	 * @param ids
	 * @return
	 */
	List<T> findByIds(@Param("ids") Collection<ID> ids);

	/**
	 * 根据名称查找
	 * 
	 * @param name
	 * @return
	 */
	List<T> findByName(@Param("name") String name);

	/**
	 * 根据名称查找，只返回一个
	 * 
	 * @param name
	 * @return
	 */
	T findOneByName(@Param("name") String name);

	/**
	 * 获取所有对象
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * 统计大小
	 * 
	 * @return
	 */
	long count();
}
