package com.example.controller;

import com.example.domain.SysLog;
import com.example.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("syslog")
public class SysLogController {

    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                            Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                            pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = iSysLogService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
