package cn.yk.demo;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <br> Project: mybbs
 * <br> Package: cn.yk.mybbs.common
 * <br> Description: 返回JSON
 * <br> Date: Created in 11:51 2016/12/19.
 * <br> Modified By
 * @author SiGen
 */
public class RenderJson {
    /**
     * <br> Description: 调用doRender返回JSON
     * <br> Date: Created in 9:56 2016/12/21.
     * <br> Modified By
     * @param response 当前会话
     * @param bean 需要转为JSON字符串的对象
     */
    public static void renderJSON(HttpServletResponse response,Object bean){
        // doRender(response, JSON.toJSONString(bean),"text/html;charset=UTF-8");
        doRender(response,JSON.toJSONString(bean),"application/json;charset=UTF-8");
    }
    /**
     * <br> Description: 返回JSON
     * <br> Date: Created in 9:56 2016/12/21.
     * <br> Modified By
     * @param response 当前会话
     * @param message 需要传输的JSON字符串
     * @param charSet 字符串所用的字符集
     */
    public static void doRender(HttpServletResponse response,CharSequence message,String charSet){
        // 设置JSON字符串的字符集
        response.setContentType(charSet);
        // Description: 向会话中写入数据
        try {
            // 获取与当前会话传送数据的窗口
            PrintWriter printWriter = response.getWriter();
            // 向当前会话返回JSON字符串格式的数据
            printWriter.write(message.toString());
            // 打印传送的数据
            System.out.println(message.toString());
            // 关闭窗口
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
