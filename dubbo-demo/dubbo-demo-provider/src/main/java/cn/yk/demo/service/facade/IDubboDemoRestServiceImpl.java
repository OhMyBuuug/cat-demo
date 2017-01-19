package cn.yk.demo.service.facade;

import cn.yk.demo.api.dto.User;
import cn.yk.demo.api.service.IDubboDemoService;
import cn.yk.demo.api.service.facade.IDubboDemoRestService;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.service.facade
 * <br> Description: 测试 Dubbo Rest 接口实现
 * <br> Date: Created in 16:49 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
@Path("dubbo/demo")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({ContentType.APPLICATION_JSON_UTF_8})
public class IDubboDemoRestServiceImpl implements IDubboDemoRestService {
    @Autowired
    private IDubboDemoService iDubboDemoService;

    /**
     * 根据用户ID查询
     * @param userToString userIdToString
     * @return
     */
    @Path("selectByUserId")
    @POST
    @Override
    public String select(String userToString) {
        int amount = 1;
        JSONObject result = new JSONObject();
        /**
         * 在CAT监控中,创建一条 Transaction记录
         * @param type 同一类型的事件将被分到同一类目中
         * @param name Transaction名称
         * t ${startTime}  dubboRestService  /dubbo-demo-provider/dubbo/demo/selectByUserId
         * 	 ...(嵌套在Cat.newTransaction与Transaction.complete()之间的CAT记录)
         * T ${finishTime} dubboRestService /dubbo-demo-provider/dubbo/demo/selectByUserId ${timeCost}
         * 必需
         */
//        Transaction dubboRestServiceTransaction = Cat.newTransaction("URL.POST","/dubbo-demo-provider/dubbo/demo/selectByUserId");

//        try{
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
//            Cat.logEvent("URL.POST", "/dubbo-demo-provider/dubbo/demo/selectByUserId", Message.SUCCESS, "这是CAT监控dubbo-cat-demo项目ing");

            /**
             *  可以用于记录交易次数和交易总额等业务指标
             *  按需调用
             */
//            Cat.logMetricForCount("dubbo-demo-PV");
//            Cat.logMetricForSum("dubbo-demo-PayAmount", amount);

            // 在此处写你的业务代码
            JSONObject params = JSON.parseObject(userToString);
            String userId = params.get("userId").toString();
            User user = iDubboDemoService.selectByUserId(userId);
            result.put("user",user);

            /**
             * 业务代码执行完毕,并且未抛出异常,则 此次执行该段代码没有出现问题,
             * Transaction记录成功
             */
//            dubboRestServiceTransaction.setStatus(Transaction.SUCCESS);
//        }catch (Exception exception){
//            // 捕获异常,向CAT报告错误,该错误将在Problem选项卡中显示
//            Cat.logError(exception);
//
//            // 在Transaction中记录这次异常
//            dubboRestServiceTransaction.setStatus(exception);
//        }finally {
//            /**
//             * 每一条Transaction结束时必须调用该方法
//             * 必需
//             */
//            dubboRestServiceTransaction.complete();
//        }

        return result.toJSONString();
    }
}
