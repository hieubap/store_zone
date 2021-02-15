package store.zone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.zone.dto.LoginDTO;
import store.zone.service.UserService;

@Controller
public class IndexController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = {"/index","/"},method = RequestMethod.GET)
  public String index(){
    return "index.html";
  }

//  @RequestMapping(value = "/login",method = RequestMethod.GET)
//  public String login(){
//    return "login.html";
//  }

//  @RequestMapping(value = {"/login"},method = RequestMethod.POST)
//  public String loginPost(@RequestBody LoginDTO login) throws Exception {
//
//    if (userService.login(login).getStatusCode() == HttpStatus.OK)
//      return "redirect:/management";
//
//    return "index.html";
//  }
//
//  @RequestMapping(value = {"/create_product"},method = RequestMethod.GET)
//  public String createProduct(){
//    return "create_product.html";
//  }
//
//  @RequestMapping(value = {"/management"},method = RequestMethod.GET)
//  public String management(){
//    return "management.html";
//  }
//
//  @RequestMapping(value = {"/product_detail"},method = RequestMethod.GET)
//  public String productDetail(){
//    return "product_detail.html";
//  }

}
