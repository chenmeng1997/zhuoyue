package com.cm.zhuoyue.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cm.zhuoyue.UserServiceApplication;
import com.cm.zhuoyue.user.config.MetaObjectsHandler;
import com.cm.zhuoyue.user.config.MybatisPlusConfig;
import com.cm.zhuoyue.user.domain.SysUserInfo;
import com.cm.zhuoyue.user.mapper.SysUserInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 陈萌
 * @date 2021/6/9 14:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserServiceApplication.class, MybatisPlusConfig.class, MetaObjectsHandler.class} )
public class SysUserTest {

    @Autowired
    private SysUserInfoMapper sysUserInfoMapper;

    @Test
    public void insert() {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setAccount("000000");
        userInfo.setPassword("000000");
        userInfo.setSalt("00000");
        userInfo.setEmail("2322051194");
        userInfo.setMobile("18338117092");
        userInfo.setFullName("ijn");
        userInfo.setStatus("1");

        sysUserInfoMapper.insert(userInfo);
        System.out.println(userInfo.getId());
    }

    @Test
    public void delBatch() {
        sysUserInfoMapper.deleteById(1);
    }

    @Test
    public void getById() {
        SysUserInfo sysUserInfo = sysUserInfoMapper.selectById(2);
        System.out.println(sysUserInfo);
    }

    /**
     * 分页测试
     */
    @Test
    public void userPage() {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setFullName("Chen1997");
        userInfo.setSource("网");
        userInfo.setAccount("201556516");
        IPage<SysUserInfo> userPage = new Page<>(1, 2);
        QueryWrapper<SysUserInfo> wrapper = setUserInfoListQueryWrapper(userInfo);
        userPage = sysUserInfoMapper.selectPage(userPage, wrapper);
        userPage.getRecords();
    }

    private QueryWrapper<SysUserInfo> setUserInfoListQueryWrapper(SysUserInfo userInfo) {
        String fullName = null;
        String source = null;
        if (StringUtils.isNotEmpty(userInfo.getFullName())){
            fullName = userInfo.getFullName();
            userInfo.setFullName(null);
        }
        if (StringUtils.isNotEmpty(userInfo.getSource())){
            source = userInfo.getSource();
            userInfo.setSource(null);
        }
        if (StringUtils.isNotEmpty(userInfo.getFullName())){
            fullName = userInfo.getFullName();
            userInfo.setFullName(null);
        }
        QueryWrapper<SysUserInfo> wrapper = new QueryWrapper<>();
        wrapper.setEntity(userInfo);
        if (StringUtils.isNotEmpty(fullName)){
            wrapper.like("FULL_NAME", fullName);
        }
        if (StringUtils.isNotEmpty(source)){
            wrapper.like("SOURCE", source);
        }
        return wrapper;
    }

}
