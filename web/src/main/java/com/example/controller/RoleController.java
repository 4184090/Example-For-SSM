package com.example.controller;

import com.example.domain.Permission;
import com.example.domain.Role;
import com.example.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping("addPermissionToRole.do")
    @Secured({"ROLE_ADMIN"})
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        iRoleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }


    @RequestMapping("findRoleByIdAndAllPermission.do")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Role role = iRoleService.findById(roleId);
        mv.addObject("role", role);
        List<Permission> permissionList = iRoleService.findOtherPermission(roleId);
        mv.addObject("permissionList", permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("save.do")
    @Secured({"ROLE_ADMIN"})
    public String save(Role role) throws Exception {
        iRoleService.save(role);
        return "redirect:findAll.do";
    }


    @RequestMapping("findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                            Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                            pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = iRoleService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(roleList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("deleteById.do")
    @Secured({"ROLE_ADMIN"})
    public String deleteById(String id) throws Exception{
        iRoleService.deleteById(id);
        iRoleService.deleteByRoleId(id);
        iRoleService.deleteByRoleIds(id);
        return "redirect:findAll.do";
    }

}
