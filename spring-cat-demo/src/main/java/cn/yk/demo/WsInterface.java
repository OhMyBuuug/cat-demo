package cn.yk.demo;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.util
 * <br> Description:
 * <br> Date: Created in 14:58 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
public class WsInterface {
    // 公共调用接口
    public static JSONObject publicWS(String url, MediaType mediaType,
                                      Map<String, Object> map) {

        JSONObject jsonObject = new JSONObject();
        Map<String, Object> resulMap = new HashMap<String, Object>();

        // url = "http://10.10.8.231:8080/imes-provider" + url;
        // url = "http://10.10.8.82:8080/imes-provider" + url;
        url = "http://127.0.0.1:8080/imes-provider" + url;
        //url = "http://10.10.8.50:9090/imes-provider" + url;

        String result = "";
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        try {
            Response response = target.request().post(
                    Entity.entity(JSON.toJSONString(map), mediaType));
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed with HTTP error code : "
                        + response.getStatus());
            }
            result = response.readEntity(String.class);
            jsonObject = JSONObject.parseObject(result);
            response.close();

        } catch (Exception e) {
            jsonObject.put("code", "0");
            jsonObject.put("message", "服务器异常，请联系管理员！");
        } finally {
            client.close();
        }
        return jsonObject;
    }
}
