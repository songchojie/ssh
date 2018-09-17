package com.oppium.service;


import com.oppium.model.User;

import java.util.List;

public interface UserService {

  List<User> getUser();

  void saveUser(User user);

  void updateUser(User user);

  void updateUserById(int id, String[] params, Object[] values);

  User getUserById(int id);

}
