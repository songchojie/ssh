package com.oppium.dao;


import com.oppium.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

  public List<User> getUser() {
    return  findByHQL("from User");
  }

  public void saveUser(User user){
   save(user);
  }

  public void updateUser(User user){
    update(user);
  }

  public void updateUserById(int id, String[] params, Object[] values){
    partUpate(id, params, values);
  }

  public User getUserById(int id){
    return findById(id);
  }
}
