package com.nowcoder.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")

    //@ResponseBody注解代表将java对象转化为json格式，以便直接在浏览器显示，不加的话，则代表返回的是网页
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }
}
