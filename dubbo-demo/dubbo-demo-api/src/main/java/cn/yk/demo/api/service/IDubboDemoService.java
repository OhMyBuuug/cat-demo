package cn.yk.demo.api.service;

import cn.yk.demo.api.dto.User;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.api.service
 * <br> Description: 测试 Dubbo 接口
 * <br> Date: Created in 16:44 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
public interface IDubboDemoService {
    public User selectByUserId(String userId);
}
