/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UserServiceImpl
 * Author:   CentreS
 * Date:     2019-06-21 10:46
 * Description: 用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.User;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.UserService;
import com.yjjk.reservation.utility.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CentreS
 * @Description: 用户管理
 * @create 2019-06-21
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Override
    public List<User> getUserInfoBySelect(User userInfo) {
        return null;
    }

    @Override
    public List<User> getUserInfoByWX(String openId, String unionId) {
        User user = new User();
        if (!StringUtils.isNullorEmpty(openId)) {
            user.setOpenId(openId);
        }
        if (!StringUtils.isNullorEmpty(unionId)) {
            user.setUnionId(unionId);
        }
        user.setStatus(0);
        return super.userMapper.getUserInfoBySelect(user);
    }

    @Override
    public List<User> getUserInfo(String name, Long phone) {
        User user = new User();
        user.setName(name);
        user.setPhone(phone);
        user.setStatus(0);
        return super.userMapper.getUserInfoBySelect(user);
    }


    @Override
    public int updateUserInfoBySelect(User userInfo) {
        userInfo.setStatus(0);
        return super.userMapper.updateUserInfoBySelect(userInfo);
    }

    @Override
    public int bandingWx(String openId, String unionId, String name, Long phone) {
        User user = new User();
        if (!StringUtils.isNullorEmpty(openId)) {
            user.setOpenId(openId);
        }
        if (!StringUtils.isNullorEmpty(unionId)) {
            user.setUnionId(unionId);
        }
        if (!StringUtils.isNullorEmpty(name)) {
            user.setName(name);
        }
        if (phone != null) {
            user.setPhone(phone);
        }
        user.setStatus(0);
        return super.userMapper.updateUserInfoByWx(user);
    }
}
