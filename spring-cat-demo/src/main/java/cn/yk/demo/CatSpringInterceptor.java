package cn.yk.demo;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.util
 * <br> Description: CAT监控Spring用的拦截器
 * <br> Date: Created in 14:24 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
public class CatSpringInterceptor implements HandlerInterceptor {
    // 统计 请求被处理 -> 页面模板渲染完成 的时间
    private ThreadLocal<Transaction> tranLocal = new ThreadLocal<Transaction>();
    // 统计 Controller处理完成返回页面 -> 页面渲染完成 的时间
    private ThreadLocal<Transaction> pageLocal = new ThreadLocal<Transaction>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Transaction t = Cat.newTransaction("URL", uri);
        Cat.logEvent("URL.Method", request.getMethod(), Message.SUCCESS, request.getRequestURL().toString());
        Cat.logEvent("URL.Host", request.getMethod(), Message.SUCCESS, request.getRemoteHost());
        tranLocal.set(t);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        String viewName = modelAndView != null ? modelAndView.getViewName() : "无该View的名称";
        Transaction t = Cat.newTransaction("View", viewName);
        pageLocal.set(t);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求-页面渲染前
        Transaction pt = pageLocal.get();
        pageLocal.remove(); // need remove
        pt.setStatus(Transaction.SUCCESS);
        pt.complete();

        // 总计
        Transaction t = tranLocal.get();
        tranLocal.remove(); // need remove
        t.setStatus(Transaction.SUCCESS);
        t.complete();
    }
}

