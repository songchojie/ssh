package com.oppium.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {

  public static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

  @Autowired
  private SessionFactory sessionFactory;

  private Class<T> clazz;

  public BaseDaoImpl() {
    Type type = getClass().getGenericSuperclass();
    clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    logger.info("DAO的真实实现类是：" + this.clazz.getName());
  }

  public Session getSession() {
    return sessionFactory.openSession();
  }


  public void save(T entity) {
    Session session = null;
    Transaction tx = null;
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      session.save(entity);
      tx.commit();
    } catch (Exception e) {
      logger.error("保存数据失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }


  }

  public void update(T entity) {
    Session session = null;
    Transaction tx = null;
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      session.update(entity);
      tx.commit();
    } catch (Exception e) {
      logger.error("修改数据失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }

  }

  public void partUpate(int id, String[] params, Object[] values) {
    Session session = null;
    Transaction tx = null;
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      String tableName = clazz.getSimpleName();
      StringBuffer hql = new StringBuffer();
      hql.append("update " + tableName + " t set ");
      for (int i = 0; i < params.length; i++) {
        hql.append("t." + params[i]).append("=?" + i +",");
      }
      hql.deleteCharAt(hql.length() - 1);
      hql.append(" where id=:id");
      Query query = session.createQuery(hql.toString());
      for (int i = 0; i < values.length; i++) {
        query.setParameter(i, values[i]);
      }
      query.setParameter("id", id);
      logger.info("部分字段更新语句是：" + hql);
      query.executeUpdate();
      tx.commit();
    } catch (Exception e) {
      logger.error("部分字段更新失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }

  }

  public void delete(Serializable id) {
    Session session = null;
    Transaction tx = null;
    T obj = findById(id);
    try {
      session = this.getSession();
      session.delete(obj);
      tx.commit();
    } catch (Exception e) {
      logger.error("删除数据失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }

  }

  public T findById(Serializable id) {
    Session session = null;
    Transaction tx = null;
    T obj = null;
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      obj = (T) session.get(clazz, id);
      tx.commit();
    } catch (Exception e) {
      logger.error("根据id查询数据失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return obj;
  }

  public List<T> findByHQL(String hql, Object... param) {
    Session session = null;
    Transaction tx = null;
    List<T> list = new ArrayList<T>();
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      Query query = session.createQuery(hql);
      for (int i = 0; i < param.length; i++) {
        query.setParameter(i, param[i]);
      }
      list = query.list();
      tx.commit();
    } catch (Exception e) {
      logger.error("根据hql语句获取数据失败", e);
      if (tx != null) {
        tx.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return list;
  }

  public List<T> queryPage(String hql, int pageNo, int pageSize) {
    Session session = null;
    Transaction tx = null;
    List<T> list = new ArrayList<T>();
    try {
      session = this.getSession();
      tx = session.beginTransaction();
      Query query = session.createQuery(hql);
      list = query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
      tx.commit();
    } catch (Exception e) {
      logger.error("分页查询失败", e);
      if(tx != null){
        tx.rollback();
      }
    } finally {
      if(session != null){
        session.close();
      }
    }
    return list;
  }

}
