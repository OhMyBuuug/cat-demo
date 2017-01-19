package cn.yk.demo.controller.hello;

import cn.yk.demo.controller.BaseController;
import cn.yk.demo.RenderJson;
import cn.yk.demo.WsInterface;
import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.controller.hello
 * <br> Description: Hello World!
 * <br> Date: Created in 13:35 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
@Controller
public class HelloWorldController extends BaseController{
    // Spring埋点示例
    @RequestMapping(value = "/spring-cat-demo/hello",method = RequestMethod.GET)
    public void hello(){
        int amount=123;
        /**
         * 在CAT监控中,创建一条 Transaction记录
         * @param type 同一类型的事件将被分到同一类目中
         * @param name Transaction名称
         * t ${startTime}  URL.GET  /spring-cat-demo/hello
         * 	 ...(嵌套在Cat.newTransaction与Transaction.complete()之间的CAT记录)
         * T ${finishTime} URL.GET /spring-cat-demo/hello ${timeCost}
         * 必需
         */
        Transaction transaction = Cat.newTransaction("URL.GET","/spring-cat-demo/hello");

        try{
            /**
             * 记录一个事件
             * @param type 事件类型,同一类型的事件将被分到同一类目中
             * @param name 事件名称
             * @param status 事件状态
             * @param nameValuePairs 你要在CAT中记录的日志或者说明,whatever U want
             * Cat.logEvent(type, name, status, nameValuePairs);
             *
             * 下面的示例将创建如下的Event
             * E ${currentTime} Url.GET /demo/hello 这是CAT监控imes项目的一个demo
             * 按需调用
             */
            Cat.logEvent("Url.GET", "/demo/hello", Message.SUCCESS, "这是CAT监控spring-cat-demo项目ing");

            /**
             *  可以用于记录交易次数和交易总额等业务指标
             *  按需调用
             */
            // 记录一个业务指标--次数
            Cat.logMetricForCount("PV");
            // 记录一个业务指标--总数
            Cat.logMetricForSum("PayAmount", amount);

            // 业务代码
            RenderJson.renderJSON(response,"Hello,world");

            // 业务代码执行完毕,并且未抛出异常,则 此次执行该段代码没有出现问题,Transaction记录成功
            transaction.setStatus(Transaction.SUCCESS);
        }catch (Exception exception){
            // 捕获异常,向CAT报告错误,该错误将在Problem选项卡中显示
            Cat.logError(exception);

            // 在Transaction中记录这次异常
            transaction.setStatus(exception);
        }finally {
            /**
             * 每一条Transaction结束时必须调用该方法
             * 必需
             */
            transaction.complete();
        }
    }
    // 与Dubbo交互示例
    @RequestMapping(value = "/spring-cat-demo/connectWithDubbo",method = RequestMethod.GET)
    public void connectWithDubbo(MediaType mediaType){
        // TODO 待完成,dubbo的REST风格的远程调用监控
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("userId","1");
        Map<String,Object> result = WsInterface.publicWS("http://127.0.0.1:9190/dubbo-demo-provider/dubbo/demo/selectByUserId",mediaType,params);

        RenderJson.renderJSON(response,result);
    }
}
