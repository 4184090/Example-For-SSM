package com.example.controller;

import com.example.domain.Role;
import com.example.domain.UserInfo;
import com.example.service.IUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("addRoleToUser.do")
    @Secured({"ROLE_ADMIN"})
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        iUserService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = iUserService.findById(userId);
        mv.addObject("userInfo", userInfo);
        List<Role> otherRoles = iUserService.findOtherRoles(userId);
        mv.addObject("otherRoles", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("findById.do")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = iUserService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("save.do")
    @Secured({"ROLE_ADMIN"})
    public String save(UserInfo userInfo) throws Exception {
        iUserService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                        Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                        pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = iUserService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(userList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("deleteById.do")
    @Secured({"ROLE_ADMIN"})
    public String deleteById(String id) throws Exception{
        iUserService.deleteById(id);
        iUserService.deleteByUserId(id);
        return "redirect:findAll.do";
    }
}
