package com.example.controller;

import com.example.domain.Permission;
import com.example.service.IPermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("save.do")
    @Secured({"ROLE_ADMIN"})
    public String save(Permission permission) throws Exception{
        iPermissionService.save(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                            Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                            pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = iPermissionService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("deleteById.do")
    @Secured({"ROLE_ADMIN"})
    public String deleteById(String id) throws Exception{
        iPermissionService.deleteById(id);
        iPermissionService.deleteByPermissionId(id);
        return "redirect:findAll.do";
    }

}
