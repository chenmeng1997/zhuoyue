package com.cm.zhuoyue.user;

import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.mapper.SysUserInfoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 陈萌
 * @date 2021/6/9 14:28
 */
@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Test
    void insert(){
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setAccount("000000");
        userInfo.setPassword("000000");
        userInfo.setSalt("00000");
        userInfo.setEmail("2322051194");
        userInfo.setMobile("18338117092");
        userInfo.setFullName("ijn");
        userInfo.setStatus("1");

        sysUserInfoMapper.insert(userInfo);
    }

    @Test
    void delBatch() {
        sysUserInfoMapper.deleteById(1);
    }

}
