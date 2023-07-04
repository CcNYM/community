package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import com.nowcoder.community.util.CommunityUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    //@ResponseBody注解代表将java对象转化为json格式，以便直接在浏览器显示，不加的话，则代表返回的是网页
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public  String getDate(){
        return alphaService.find();
    }

    //传统的请求与相应方法，即通过servletAPI
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+":"+value);
        }
        System.out.println(request.getParameter("code"));

        // 返回相应数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>牛客网</h1>");
        writer.close();
    }

    //使用springmvc的方式进行请求转发会更快捷
    //get请求
    // /student?current=1&limit=20
    @RequestMapping(path="/students",method = RequestMethod.GET)
    @ResponseBody
    public String getSudents(int current,int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }
    //使用@RequestParam注解接受参数会更加规范,required=false代表可以不传，defaultValue指代无值传入时的默认值
    @RequestMapping(path="/students2",method = RequestMethod.GET)
    @ResponseBody
    public String getSudents2(
            @RequestParam(name="current",required=false,defaultValue="1") int current,
            @RequestParam(name="limit",required=false,defaultValue="10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }

    // /student/123  动态的将参数拼如到url中
    @RequestMapping(path="/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getSudent(@PathVariable("id") int id) {
        System.out.println(id);
        return "a student";
    }

    //POST请求
    @RequestMapping(path="/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应html数据，复杂的方法，通过返回ModelAndView，注意这个只能返回template下的
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","30");
        mav.setViewName("/demo/view");
        return mav;
    }

    //响应html数据，简单的方法，注意这个只能返回template下的
    @RequestMapping(path = "/teacher2", method = RequestMethod.GET)
    public String getTeacher2(Model model) {
        model.addAttribute("name","张三");
        model.addAttribute("age","30");
        return "/demo/view";
    }

    //相应json数据（异步请求，即当前网页不刷新，但是网页中的一些内容改变，即在这个过程中请求了服务器）
    //java对象-》json字符串-》js对象 json是跨语言环境中的一个方式
    @RequestMapping(path="/emp",method=RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        return emp;
    }
    //cookie示例,返回给浏览器cookie。 之后浏览器Requestheader中就会带有该cookie访问服务器，前提访问路径在cookie的生效路径下。
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());

        //设置cookie的生效范围,即在其子目录下生效
        cookie.setPath("/community/alhpa");

        //设置cookie的有效时间，其默认是存在内存中，浏览器关闭就消失, 单位是秒
        cookie.setMaxAge(60 * 10);

        response.addCookie(cookie);

        return "set cookie";

    }

    @RequestMapping(path = "/cookie/get",method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){
        System.out.println(code);
        return "get cookie";
    }

    //ssesion示例
    @RequestMapping(path = "/session/set",method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){
        session.setAttribute("id",1);
        session.setAttribute("name","test");
        return "set session";
    }

    @RequestMapping(path = "/session/get",method = RequestMethod.GET)
    @ResponseBody
    public String get(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "set session";
    }



}
