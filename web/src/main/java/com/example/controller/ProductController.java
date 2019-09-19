package com.example.controller;

import com.example.domain.Product;
import com.example.service.IProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("save.do")
    @Secured({"ROLE_ADMIN"})
    public String save(Product product) throws Exception {
        iProductService.save(product);
        return "redirect:findAll.do";
    }

    @RequestMapping("findAll.do")
    @Secured({"ROLE_ADMIN","ROLE_USER","ROLE_GUEST"})
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1")
                                            Integer page, @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer
                                            pageSize) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> productList = iProductService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo(productList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list");
        return mv;
    }
}
