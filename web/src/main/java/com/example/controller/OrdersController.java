package com.example.controller;

import com.example.domain.Orders;
import com.example.domain.Traveller;
import com.example.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

    @RequestMapping("/findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                        Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                        pageSize) throws Exception {
        List<Orders> ordersList = iOrdersService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-list");
        mv.addObject("pageInfo", pageInfo);
        return mv;
    }

    @RequestMapping("/findById.do")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findById(String id) throws Exception {
        Orders orders = iOrdersService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("orders-show");
        mv.addObject("orders", orders);
        return mv;
    }

}
