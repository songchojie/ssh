package com.oppium.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {

  void save(T entity);

  void update(T entity);

  void partUpate(int id, String[] name, Object[] values);

  void delete(Serializable id);

  T findById(Serializable id);

  List<T> findByHQL(String hql, Object... param);

  List<T> queryPage(String hql, int pageNo, int pageSize);

}
