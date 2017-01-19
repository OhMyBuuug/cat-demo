package cn.yk.demo.service;

import cn.yk.demo.api.dto.User;
import cn.yk.demo.api.service.IDubboDemoService;
import cn.yk.demo.dao.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br> Project: cat-demo
 * <br> Package: cn.yk.demo.service
 * <br> Description: 测试 Dubbo 接口实现
 * <br> Date: Created in 16:48 2017/1/18.
 * <br> Modified By
 *
 * @author SiGen
 */
@Service
public class IDubboDemoServiceImpl implements IDubboDemoService {
    @Autowired
    private userMapper usermapper;

    @Override
    public User selectByUserId(String userId) {
        User user=usermapper.selectByPrimaryKey(Integer.parseInt(userId));
        return user;
    }
}
