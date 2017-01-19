package cn.yk.demo.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.controller
 * <br> Description:
 * <br> Date: Created in 13:29 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;

    public HttpSession session;

    @ModelAttribute
    public void inital(HttpServletRequest request,HttpServletResponse response){
        this.request=request;
        this.response=response;
        this.session= request.getSession();
        // 伪造一组session数据
        session.setAttribute("username","admin");
    }
}
