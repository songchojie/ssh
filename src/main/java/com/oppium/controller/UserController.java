package com.oppium.controller;

import com.oppium.model.User;
import com.oppium.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;


  @RequestMapping("index")
  public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws Exception{

 //对于添加了@Version的注解，我们不需要手动去控制，每一次save操作会在原来的基础上+1，如果初始为null，则springdata自动设置其为0。
//    User user = new User();
//    user.setName("自强不息");
//    user.setAge(27);
//    user.setTime("九零后");
//    userService.saveUser(user);
    List<User> list = userService.getUser();
    request.setAttribute("userList", list);
    return new ModelAndView("index");
  }

  @RequestMapping("update")
  public void updateUser(){
    User user = userService.getUserById(6);
    user.setName("永不言弃");
    userService.updateUser(user);
  }

  //@Query中的update，delete操作是不会触发springdata的相关代理操作的，而是转化为原生sql的方式
//  @RequestMapping("updateBySql")
//  public void updateUserById(){
//    User user = userService.getUserById(6);
//    userService.updateUserById(6, new String[]{"name"}, new Object[]{"never give up"});
//  }

//  主线程和新线程获取了同一行记录，并且新线程优先提交了事务，版本号一致，修改成功。等到了主线程再想save提交事务时，便得到一个版本号不一致的异常
//  @RequestMapping("updateUserByThread")
//  public void updateUserByThread(){
//    User user = userService.getUserById(6);
//    user.setName("自强不息");
//    new Thread(new Runnable() {
//      public void run() {
//        User user = userService.getUserById(6);
//        userService.updateUser(user);
//      }
//    }).start();
//    userService.updateUser(user);
//
//  }

}
