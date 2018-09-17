package com.oppium.service;

import com.oppium.dao.UserDao;
import com.oppium.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

  @Autowired
  private UserDao userDao;

  public List<User> getUser(){
    return userDao.getUser();
  }

  public void saveUser(User user){
    userDao.saveUser(user);
  }

  public void updateUser(User user){
    userDao.updateUser(user);
  }

  public void updateUserById(int id, String[] params, Object[] values){
    userDao.updateUserById(id, params, values);
  }

  public User getUserById(int id){
    return userDao.getUserById(id);
  }
}
